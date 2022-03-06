
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
2. How does GC work
   1. Stop the world 
   2. GC roots
3. Describe G1 collector
4. Describe ZGC collector
5. Compare G1 vs ZGC
6. Describe bytecode (groups, prefix/suffix, operand types)
7. How is bytecode generated and how can be viewed
8. Describe operand stack and local variables array
9. Describe how does bytecode interpretation works in runtime
10. What is JIT compilation, how does it work


