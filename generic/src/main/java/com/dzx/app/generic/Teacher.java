package com.dzx.app.generic;

/** 未传入泛型参数时，与泛型类的定义相同，在声明的时候，需要将泛型的声明也一起加到类里
 * 即：class Teacher<T> implements IPerson<T>
 * 如果不声明泛型，如：class Teacher implements IPerson<T>，编译器会报错："Unknown class"
 * */
public class Teacher<T> implements IPerson<T>{
		@Override
		public T next() {
				return null;
		}
}
