package com.didispace.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.*;

/**
* @Description 于对java数据进行序列化和反序列化处理 
* @Author daiwei
* @Date 2017年12月6日 下午4:06:57
*
 */
public class SerializeUtil {  
      
    public final static String CHARSET_ISO88591="iso-8859-1";
    /** 
      * @Title: serialize 
      * @Description: java对象序列化 <br> 
      * eg:<br> 
      *   Map<String,String> map = new HashMap<String,String>();<br> 
      *   map.put("test","序列化");<br> 
      *   String serializedMapStr=SerializeUtil.serialize(map); 
      * @param original 要进行序列化的java对象 
      * @return String 序列化的后的值 
      * @throws IOException
      *  
      *  
      */  
    public static String serialize(Object original) throws IOException {
        if(null==original) return null;  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(original);    
        byte[] str = baos.toByteArray();  
        oos.close();  
        baos.close();  
        return new String(str,CHARSET_ISO88591);
    }  
      
    /** 
      * @Title: deserialize 
      * @Description: 序列化的String对象的反序列化<br> 
      * 需要自己进行强制转换得到最终类型<br>  
      * eg:<br> 
      *   Map newmap = (Map)SerializeUtil.deserialize(serializedMapStr);  
      * @param serializedstr 经序列化处理过的信息 
      * @return Object 反序列化后生成的Object。<br> 
      * @throws IOException
      * @throws UnsupportedEncodingException
      * @throws ClassNotFoundException
      *  
      *  
      */  
    public static Object deserialize(String serializedstr) throws UnsupportedEncodingException, IOException, ClassNotFoundException {
        if(null==serializedstr)return null;  
        BufferedInputStream bis=new BufferedInputStream(new ByteArrayInputStream(serializedstr.getBytes(CHARSET_ISO88591)));
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();  
        bis.close();  
        return obj;  
    }  
      
    public static byte[] objectToByteArray(Object original) throws IOException {
        if (null == original)  
            return null;  
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (ObjectOutputStream oout = new ObjectOutputStream(bout);) {
            oout.writeObject(original);  
        }  
        return bout.toByteArray();  
    }   
      
    @SuppressWarnings("unchecked")
    public static Map<String, Object> byteArrayToObject(byte[] bytearry) throws IOException, ClassNotFoundException {
        if (bytearry.length==0) return null;  
        return (Map<String, Object>)new ObjectInputStream(new ByteArrayInputStream(bytearry)).readObject();
    }  
    
    
    public static <T extends Serializable> String serializationObjectByKryo(T obj) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deserializationObjectByKryo(String obj,
                                                                         Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());
 
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (T) kryo.readClassAndObject(input);
    }
 
    public static <T extends Serializable> String serializationListByKryo(List<T> obj,
                                                                          Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    @SuppressWarnings("unchecked")
	public static <T extends Serializable> List<T> deserializationListByKryo(String obj,
                                                                             Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (List<T>) kryo.readObject(input, ArrayList.class, serializer);
    }
 
    public static <T extends Serializable> String serializationMapByKryo(
            Map<String, T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Map<String, T> deserializationMapByKryo(
            String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (Map<String, T>) kryo.readObject(input, HashMap.class,
                serializer);
    }
 
    public static <T extends Serializable> String serializationSetByKryo(Set<T> obj,
                                                                         Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
 
        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return new String(new Base64().encode(b));
    }
 
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Set<T> deserializationSetByKryo(
            String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
 
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
 
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);
 
        ByteArrayInputStream bais = new ByteArrayInputStream(
                new Base64().decode(obj));
        Input input = new Input(bais);
        return (Set<T>) kryo.readObject(input, HashSet.class, serializer);
    }
}  