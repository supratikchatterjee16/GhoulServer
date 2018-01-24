package ghoul.util;

import java.lang.Class;
import java.lang.ClassLoader;

class DynamicClassLoader extends ClassLoader{
    DynamicClassLoader(){}
    public void load(String filename){
        this = this.class.getClassLoader();
        
    }
}
