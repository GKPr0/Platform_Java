
# Garbage collectors
0. General Garbage Collectors info
- GC watches which objects can we reach from our application through a chain of references and frees up the ones we can't reach.
- Root references are taken from the stack.
- Relocate objects from fragmented memory areas to free areas in a more compact format.
- Properties of individual phases:
   - a <b>parallel</b> phase can run on multiple GC threads
   - a <b>serial</b> phase runs on a single thread
   - a <b>stop-the-world</b> phase can't run concurrently with application code
   - a <b>concurrent</b> phase can run in the background, while our application does its work
   - an <b>incremental</b> phase can terminate before finishing all of its work and continue it later

1. G1
   1. https://www.oracle.com/technetwork/tutorials/tutorials-1876574.html  
   2. https://www.youtube.com/watch?v=OhPGN2Av44E
   - Generational GC
   - G1 performs a concurrent global marking phase to determine the liveness of objects throughout the heap. After which it knows which regions are mostly empty.
   - It collects in these regions first, which usually yields a large amount of free space -> This is why this method of garbage collection is called Garbage-First
   - G1 concentrates its collection and compaction activity on the areas of the heap that are likely to be full of reclaimable objects, that is, garbage
   - G1 uses a pause prediction model to meet a user-defined pause time target and selects the number of regions to collect based on the specified pause time target.
   - <b>Evacuation</b>
      - G1 copies objects from one or more regions of the heap to a single region on the heap, and in the process both compacts and frees up memory.
      - Performed in parallel (if possible) to decrese pause time and increase thoughput.
   - <b>Footprint</b>
      - Larger JVm process size then older methods (Parallel, CMS)
      - <b>Remembered Sets</b>
         - Track object references into a given region. 
         - There is one RSet per region in the heap. 
         - The RSet enables the parallel and independent collection of a region. 
         - The overall footprint impact of RSets is less than 5%
      - <b>Collection Sets</b>
         - The set of regions that will be collected in a GC.
         - All live data in a CSet is evacuated (copied/moved) during a GC.
         - Sets of regions can be Eden, survivor, and/or old generation. 
         - CSets have a less than 1% impact on the size of the JVM.
   - <b>Heap Structure</b>
      - The heap is one memory area split into many fixed sized regions.
      - Region size is chosen by the JVM at startup. 
      - The JVM generally targets around 2000 regions varying in size from 1 to 32Mb.
      - These regions are mapped into logical representations of <b>Eden, Survivor, and old generation spaces</b>. ->This provides greater flexibility in memory usage.
      - Live objects are evacuated (i.e., copied or moved) from one region to another
      - Regions are designed to be collected in parallel with or without stopping all other application threads.
      - In addition, there is a fourth type of object known as <b>Humongous regions</b>. These regions are designed to hold objects that are 50% the size of a standard region or larger. They are stored as a set of contiguous regions. 
      - Finally the last type of regions would be the unused areas of the heap.
   - <b>Young Generation Garbage Collections</b>
      - Works with Eden and Survivor regions.
      - Young generation memory is composed of a set of non-contiguous regions. This makes it easy to resize when needed.
      - Young generation garbage collections are stop the world events. All application threads are stopped for the operation.
      - Any older objects that have reached their aging threshold are promoted to old generation.
      - Eden size and survivor size is calculated for the next young GC.
      - The young GC is done in parallel using multiple threads.
      - Live objects are copied to new survivor or old generation regions.
   - <b>Old Generation Garbage Collections</b>
      -  G1 collector is designed to be a low pause collector for old generation objects.
      1. <b>Initial Marking Phase</b> (Stop the world)
         - Mark survivor regions (root regions) which may have references to objects in old generation.
      2. <b>Concurrent Marking Phase</b>
         - Traverse the tenured generation object graph for reachable objects
         - Liveness information is calculated concurrently while the application is running.
         - This liveness information identifies which regions will be best to reclaim during an evacuation pause.
      3. <b>Remark Phase</b> (Stop the world)
         - Empty regions are removed and reclaimed
         - Region liveness is now calculated for all regions.
      4. <b>Copying/Cleanup Phase</b>  
         - Performs accounting on live objects and completely free regions. (Stop the world)
         - Scrubs the Remembered Sets. (Stop the world)
         - Reset the empty regions and return them to the free list. (Concurrent)
         - G1 selects the regions with the lowest "liveness", those regions can be collected the fastest.
         - Young and old generations are collected at the same time.
         - Old generation regions are selected based on their liveness.
      4. <b>After Copying/Cleanup Phase</b> 
         - The regions selected have been collected and compacted into the old and young generations regions and marked as recently copied

2. ZGC
   1. https://www.baeldung.com/jvm-zgc-garbage-collector
   2. https://www.youtube.com/watch?v=88E86quLmQA

   - Scalable Low-Latency Garbage Collector
   - Max pause time 1 ms (stop the world) independent on heap size
   - Capable of handling multi-terabyte heaps
   - Ragion based GC
   - Almost everything is concurrent
      - Marking 
      - Compaction
      - Reference Processing
      - Class unloading etc..
   - <b>Colored Pointers</b>
      - 64 bits architectures usually dont use whole address space for object reference (44 bits -> 16TB)
      - So few bits (4) of address are used as metadata for GC.
      - These bits called colors contain infomration such as object has been marked, relocated, etc.
      - <b>finalizable bit</b> –> the object is only reachable through a finalizer
      - <b>remap bit</b> – the reference is up to date and points to the current location of the object (see relocation)
      - <b>marked0 and marked1 bits</b> – these are used to mark reachable objects
   - <b>Load Barrier</b>
      - Small piece of code injected by JIT when loading an object reference from heap (for example, when we access a non-primitive field of an object.)
      - Checks if the loaded object ref has a "bad" color -> if so take action to heal it
      - Or in other words ZGC may perform some processing on the reference before we get it. For example it may remap the object to some other more convenient place.
      - Load barriers fix the references pointing to relocated objects with a technique called remapping.
   - <b>Remapping</b>
      1. Checks whether the remap bit is set to 1. If so, it means that the reference is up to date, so can safely we return it.
      2. Then we check whether the referenced object was in the relocation set or not. If it wasn't, that means we didn't want to relocate it. To avoid this check next time we load this reference, we set the remap bit to 1 and return the updated reference.
      3. Now we know that the object we want to access was the target of relocation. The only question is whether the relocation happened or not? If the object has been relocated, we skip to the next step. Otherwise, we relocate it now and create an entry in the forwarding table, which stores the new address for each relocated object. After this, we continue with the next step.
      4. Now we know that the object was relocated. Either by ZGC, us in the previous step, or the load barrier during an earlier hit of this object. We update this reference to the new location of the object (either with the address from the previous step or by looking it up in the forwarding table), set the remap bit, and return the reference
   - <b>Marking Phases</b>
      - Find the reachable objects and mark then -> Colored pointers
      1. Start phase (stop-the world)
         - We look for root references and mark them
         - Root references are the starting points to reach objects in the heap
         - For example, local variables or static fields. 
      2. Traverse the object graph phase (concurrent)
         - We traverse the object graph, starting from the root references. We mark every object 
         we reach. Also, when a load barrier detects an unmarked reference, it marks it too.
      3. End phase (stop-the-world)
         - Synchronization purpose 
         - Handle some edge cases, like weak references.
   - <b>Relocation phases</b>
      1. A concurrent phase, which looks for blocks, we want to relocate and puts them in the relocation set.
      2. A stop-the-world phase relocates all root references in the relocation set and updates their references.
      3. A concurrent phase relocates all remaining objects in the relocation set and stores the mapping between the old and new addresses in the forwarding table.
      4. The rewriting of the remaining references happens in the next marking phase. This way, we don't have to traverse the object tree twice. Alternatively, load barriers can do it, as well.
   


# Bytecode
1. https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html
2. https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html
3. https://medium.com/swlh/an-introduction-to-jvm-bytecode-5ef3165fae70
4. https://www.youtube.com/watch?v=e2zmmkc5xI0


# Exam questions
1. Describe JVM heap and stack. Which variables are stored on heap and which on stack
 - JVM is stack based.
 - JVM heap contains objects, array, definitions of classes, etc.
 - JVM stack contains local variables, method arguments, etc.
2. How does GC work
   1. Stop the world 
   2. GC roots
3. Describe G1 collector
4. Describe ZGC collector
5. Compare G1 vs ZGC
6. Describe bytecode (groups, prefix/suffix, operand types)
   - Groups indicatas the parts of the bytecode that are related. At he beginign of the group there is a description of the group containing the max stack size, number of locals and number of arguments
   - Preffix indicates types of the variable on which will be the operation performed. iload -> integer, aload -> object reference, etc.
   - Suffix indicates index of local variable. aload_0 means load the first local variable in case of non static method it is this.
   - Oprarand types:
      - i -> integer
      - l -> long
      - f -> float
      - d -> double
      - s -> short
      - b -> byte
      - c -> char
      - a -> object reference
7. How is bytecode generated and how can be viewed
   - Is generated using Java compiler (javac) -> *.class files
   - Can be viewed using javap
8. Describe operand stack and local variables array
   - Operand stack descripes how the various individual bytecode operations get their input, and how they provide their output. For example to use iadd we firstly need to push 2 integers to the stack
   - Local varialbes array are consider as a reference type objects and to work with them we use xaload, xastora, etc. Where x is the any operand type.
9. Describe how does bytecode interpretation works in runtime
   - Classloader loads a *.class files that contains the bytecode and creates a class object
   - Class object is used to create an instance of the class
   - Then it works like descriped below
10. What is JIT compilation, how does it work
   - The Just-In-Time (JIT) compiler is a component of the Java™ Runtime Environment that improves the performance of Java applications at run time.

   - Java programs consists of classes, which contain platform-neutral bytecodes that can be interpreted by a JVM on many different computer architectures. At run time, the JVM loads the class files, determines the semantics of each individual bytecode, and performs the appropriate computation. The additional processor and memory usage during interpretation means that a Java application performs more slowly than a native application. The JIT compiler helps improve the performance of Java programs by compiling bytecodes into native machine code at run time.

   - The JIT compiler is enabled by default, and is activated when a Java method is called. The JIT compiler compiles the bytecodes of that method into native machine code, compiling it "just in time" to run. When a method has been compiled, the JVM calls the compiled code of that method directly instead of interpreting it. Theoretically, if compilation did not require processor time and memory usage, compiling every method could allow the speed of the Java program to approach that of a native application.

   - JIT compilation does require processor time and memory usage. When the JVM first starts up, thousands of methods are called. Compiling all of these methods can significantly affect startup time, even if the program eventually achieves very good peak performance.

   - In practice, methods are not compiled the first time they are called. For each method, the JVM maintains a call count, which is incremented every time the method is called. The JVM interprets a method until its call count exceeds a JIT compilation threshold. Therefore, often-used methods are compiled soon after the JVM has started, and less-used methods are compiled much later, or not at all. The JIT compilation threshold helps the JVM start quickly and still have improved performance. The threshold has been carefully selected to obtain an optimal balance between startup times and long term performance.

   - After a method is compiled, its call count is reset to zero and subsequent calls to the method continue to increment its count. When the call count of a method reaches a JIT recompilation threshold, the JIT compiler compiles it a second time, applying a larger selection of optimizations than on the previous compilation. This process is repeated until the maximum optimization level is reached. The busiest methods of a Java program are always optimized most aggressively, maximizing the performance benefits of using the JIT compiler. The JIT compiler can also measure operational data at run time, and use that data to improve the quality of further recompilations.



