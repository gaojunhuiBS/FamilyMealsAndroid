#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
public class ${NAME}{
    private static volatile ${NAME} ourInstance =null;

    public static ${NAME} getInstance() {
    if(null==ourInstance){
    synchronized(${NAME}.class){
    if(null==ourInstance){
    ourInstance=new ${NAME};
    }
    }}
        return ourInstance;
    }

    private ${NAME}() {
    }
}

