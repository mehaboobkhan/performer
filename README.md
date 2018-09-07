# performer

This can be used to log the performance of an application in a form of hierarchy in a form of json. Multi-threading is supported.

Methods:

1. Performer performer = Performer.getInstance();
    - creates a singleton instance

2. startCall(String name, String parentName, String type)
    - name: name of method 
      parentName: name of the parentfunction. Can be null for starting method
      type: a) Type.NORMAL - for normal methods
            b) Type.Thread - for threads. For thread call this method inside the thread and give or append thread name to the name.
            
3. endCall(String name, String parentName, String type)
    - every startCall should have a corresponding endCall
    
4. compute()
    - computes the performance and returns the result in json string.
