//CPSC501 Assignment II
//Fabio Fahme 
//UID: 30034797


import java.lang.reflect.*;
import java.util.*;

//
//1) The name of the declaring class
//2) The name of the immediate super-class
//		a) Always explore the super-class immediately and recursively (regardless of recursive parameter)
//3) The name of each interface the class implements
//		a) Always explore the super-class immediately and recursively (regardless of recursive parameter)
//4) The constructors the class declares. For each, also find the following:
//		a) The name
//		b) The parameter types
//		c) The modifiers
//5) The methods the class declares. For each, also find the following:
//		a) The name
//		b) The exceptions thrown
//		c) The parameter types
//		d) The return type
//		e) The modifiers
//6) The fields the class declares. For each, also find the following:
//		a) The name
//		b) The type
//		c) The modifiers
//		d) The current value of each field



public class Inspector1 {

		String border = "==============================";
		
		
	    public void inspect(Object obj, boolean recursive) throws IllegalArgumentException, IllegalAccessException {
	        Class c = obj.getClass();
	        Vector recursiveFieldCheck = new Vector();
	        inspect(c, obj, recursive, 0, recursiveFieldCheck);
	    }

	    private void inspect(Class c, Object obj, boolean recursive, int depth, Vector recursiveFieldCheck) throws IllegalArgumentException, IllegalAccessException {
//	    printStartInspectionBorder();
//	    System.out.println();
	    start();
	    	
//	    printClassBorder();
//	    classInfo(c, obj, recursive);
//	    printSuperClassBorder();
//	    superClassInfo(c, obj, recursive);
	    startClass(c, obj, recursive);
	    
//	    printInterfaceBorder();
//	    interfaceInfo(c, obj, recursive);
//	    System.out.println();
	    startInterface(c, obj, recursive);
	    
//	    printConstructorBorder();
//	    constructorInfo(c, obj, recursive);
//	    System.out.println();
	    startConstructor(c, obj, recursive);
	    
//	    printMethodBorder();
//	    methodInfo(c, obj, recursive);
//	    System.out.println();
	    startMethod(c, obj, recursive);
	    
//	    printFieldBorder();
//	    fieldInfo(c, obj, recursive,  recursiveFieldCheck);
//	    System.out.println();
	    startField(c, obj, recursive, recursiveFieldCheck);
	    
//	   printFieldRecursionBorder();
//	   if(recursive)
	    fieldInfoRecursive(c, obj, recursive, depth, recursiveFieldCheck);
	    startFieldRecursion(c, obj, recursive, depth, recursiveFieldCheck);

	    
	    if(c.isArray()){
            System.out.println("Array " + c.getComponentType().getName());
            
            Object [] arrayOfObjects = new Object [Array.getLength(obj)];
            	for(int i = 0; i<Array.getLength(obj);i++)
            		arrayOfObjects[i] = Array.get(obj, i);
            printArrayElements(arrayOfObjects);
        }
	   
	}
	   
	    
	   
	   public void printArrayElements(Object [] a) {
		   for(int i = 0; i < a.length;i++) {
			   if(a[i] != null) {
			   Object value = Array.get(a[i], i);
		       if (a[i] instanceof Integer ||
		    		   a[i] instanceof Float ||
		    		   a[i] instanceof Double ||
		    		   a[i] instanceof Boolean ||
		    		   a[i] instanceof Character)

	            System.out.println(String.valueOf(a[i]));
			   
		     
			   else
			   System.out.println("null");
			   }
		   }
	   }
	    
	    public void start() {
	    	printStartInspectionBorder();
	 	    System.out.println();
	    }
	    
	    public void startClass(Class c, Object obj, boolean recursive) throws IllegalArgumentException, IllegalAccessException {
	    	 printClassBorder();
	 	    classInfo(c, obj, recursive);
	 	    printSuperClassBorder();
	 	    superClassInfo(c, obj, recursive);
	    }
	    
	    
	    public void startInterface(Class c, Object obj, boolean recursive) {
	    	 printInterfaceBorder();
	 	    interfaceInfo(c, obj, recursive);
	 	    System.out.println();
	    }
 
	    public void startConstructor(Class c, Object obj, boolean recursive) {
	    	printConstructorBorder();
	  	    constructorInfo(c, obj, recursive);
	  	    System.out.println();
	    }
 
	    public void startMethod(Class c, Object obj, boolean recursive) {
	    	printMethodBorder();
		    methodInfo(c, obj, recursive);
		    System.out.println();
	    }
 
	    public void startField(Class c, Object obj, boolean recursive, Vector recursiveFieldCheck) throws IllegalArgumentException, IllegalAccessException {
	    	printFieldBorder();
	 	    fieldInfo(c, obj, recursive,  recursiveFieldCheck);
	 	    System.out.println();
	    }
 
	    public void startFieldRecursion(Class c, Object obj, boolean recursive, int depth, Vector recursiveFieldCheck) throws IllegalArgumentException, IllegalAccessException {
	    printFieldRecursionBorder();
	 	   if(recursive)
	 		   fieldInfoRecursive(c, obj, recursive, depth, recursiveFieldCheck);
	    }
 
	    public void printFieldRecursionBorder() {
	    	System.out.println(border + "Entering Fields" + border);
	    }
	    
	    public void fieldInfoRecursive(Class c, Object obj, boolean recursive, int depth,  Vector recursiveFieldCheck) throws IllegalArgumentException, IllegalAccessException {
	    	System.out.println("Hello?");
	    	while(recursiveFieldCheck.elements().hasMoreElements()) {
	    		System.out.println("DID I GET INSIDE HERE?????????????????????????");
	    		System.out.println();
	    		Field field = (Field) recursiveFieldCheck.elements().nextElement();
	    		System.out.println("Field: " + field.getName());
	    		System.out.println(border + "Start Recursing Call" + border);
	    		if(!field.getType().isPrimitive() && field.get(obj) != null)
	        	inspect(c, field.get(obj), recursive, depth, recursiveFieldCheck);
	    		else continue;
	        	System.out.println(border+ "End Recursing Call" + border);
	    	}	
	    }
	    
	    
	    public void printSuperClassBorder() {
	    	System.out.println(border + "Superclasses" + border);
	    }
	    
	    public void printInterfaceBorder() {
	    	System.out.println(border + "Interfaces" + border);
	    }
	    
	    public void printMethodBorder() {
	    	System.out.println(border + "Methods" + border);
	    }
	    
	    
	    public void printFieldBorder() {
	    	System.out.println(border + "Fields" + border);
	    }
	    
	    public void methodInfo(Class c, Object obj, boolean recursive) {
	    	Method [] classMethods = c.getDeclaredMethods();
	    	for(int i = 0; i < classMethods.length; i++) {
	    		printMethodInfo(classMethods[i]);
	    	}
	    }
	    
	    public void printMethodInfo(Method classMethods) {
	    	System.out.println("Method Name: " + classMethods.getName());
    		methodParameters(classMethods);
    		methodModifier(classMethods);
    		methodException(classMethods);
    		methodReturn(classMethods);
    		System.out.println();
	    }
	    
	    public void methodModifier(Method method) {
	    	System.out.println("Modifier: " + Modifier.toString(method.getModifiers()));
	    }
	    
	    public void methodReturn(Method method) {
	    	System.out.println("Return: " + method.getReturnType().toString());
	    }
	    
	    public void methodException(Method method) {
	    	Class [] exception = method.getExceptionTypes();
	    	for(int i = 0; i < exception.length; i++) {
	    		System.out.println("Exceptions Thrown: " + exception[i].toString());
	    		if(exception[i] == null) {
	    			System.out.println("Exception Thrown: NULL");
	    		}
	    	}
	    }
	    
	    public void methodParameters(Method method) {
	    	Class [] parameters = method.getParameterTypes();
	    	int counter = 1;
	    	for(int i = 0; i < parameters.length ;i++, counter++) 
	    		System.out.println("Parameter #" + counter + " Type: " + parameters[i].toString());
	    }
	    
	    public void printStartInspectionBorder() {
	    	System.out.println(border + "Starting Inspection" + border);
	    }
	    
	    public void printClassBorder() {
	    	System.out.println(border + "Class Information" + border);
	    }
	    
	    public void printConstructorBorder() {
	    	System.out.println(border + "Constructor Information" + border);
	    }
	    
	    public void classInfo(Class c, Object obj, boolean recursive) {
	    	System.out.println("Class Name: " + getClassName(c));
	    }
	    
	    public String getClassName(Class c) {
	    	return c.getName();
	    }
	    
	    public String getSuperClassName(Class c) {
	    	return c.getName();
	    }
	    
	    public void superClassInfo(Class c, Object obj, boolean recursive) throws IllegalArgumentException, IllegalAccessException {
	    	Class superC = c.getSuperclass();
	    	if(superC != null) {
	    		System.out.println("Superclass name: " + getSuperClassName(superC) + " ---> of Class: " + getClassName(c));
	    		superClassInfo(superC, obj, recursive);
	    		interfaceInfo(superC, obj, recursive);
	    	}
	    }
	   
	    
	    public void fieldInfo(Class c, Object obj, boolean recursive, Vector recursiveFieldCheck) throws IllegalArgumentException, IllegalAccessException {
	    	Field [] classFields = c.getDeclaredFields();
	    	
	    	for(int i = 0; i < classFields.length; i++){
	    		classFields[i].setAccessible(true);
	    		if(classFields[i].getType().isPrimitive())
	    			System.out.println("Field Name: " + classFields[i].getName());
	    	
	    		else 
	    			arrayPrint(classFields[i], obj);
	    		
	    	//	if(!classFields[i].getType().isPrimitive() && classFields[i].get(obj) != null)
	    			recursiveFieldCheck.addElement(classFields[i]);
	    	//	else
	    	//		;
	    			
	    	System.out.println("Type: " + classFields[i].getType().toString());
	    	System.out.println("Modifiers: " + Modifier.toString(classFields[i].getModifiers()));
	    	}
	    }
	    	
	    	public void arrayPrint(Field classFields, Object obj) throws IllegalArgumentException, IllegalAccessException {
	    		if(classFields.getType().isArray()) {
    				System.out.println("Array Length: " + Array.getLength(classFields.get(obj)));
    			
    			
    			System.out.println("Array Elements: ");
    			for(int j = 0; j < Array.getLength(classFields.get(obj));j++) 
    				System.out.print(Array.get(classFields.get(obj), j) + ", ");
    			}
	    	}
	    	
	    
	    public void constructorInfo(Class c, Object obj, boolean recursive) {
	    	int counter=1;
	    	Constructor [] classConstructor = c.getDeclaredConstructors();
	    	for(Constructor constructor : classConstructor) {
	    		constructor.setAccessible(true);
	    		printConstructorInfo(constructor, counter);
	    		counter++;
	    	}
	    }
	    
	    public void printConstructorInfo(Constructor c, int counter) {
	    	System.out.println("Constructor #"+counter+" Name: " + c.getName());
    		getParameters(c);
    		getModifiers(c);
    		System.out.println();
	    }
	    
	    public void getModifiers(Constructor constructor) {
	    	System.out.println("Modifiers: " + Modifier.toString(constructor.getModifiers()));
	    }
	    
	    
	    public void getParameters(Constructor constructor) {
	    	Class [] constructorParameters = constructor.getParameterTypes();
	    	int counter = 1;
	    	for(Class parameter : constructorParameters)
	    		System.out.println("Parameter #" + counter +" Name: " + parameter.getName());
	    }
	    
	    public void interfaceInfo(Class c, Object obj, boolean recursive) {
	    	Class [] classInterface = c.getInterfaces();
	    	for(int i=0; i < classInterface.length;i++)
	    		System.out.println("Interface: " + classInterface[i]);
	    }

	}
