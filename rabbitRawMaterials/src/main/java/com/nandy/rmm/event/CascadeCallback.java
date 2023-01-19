//package com.nandy.rmm.event;
//
//import java.lang.reflect.Field;
//import java.util.List;
//
////import org.springframework.data.mongodb.core.MongoOperations;
////import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.util.ReflectionUtils;
//
//
//public class CascadeCallback implements ReflectionUtils.FieldCallback {
//
//	private Object source;
//	//private MongoOperations mongoOperations;
//	
//	CascadeCallback(final Object source, final MongoOperations mongoOperations) {
//		this.source = source;
//		this.mongoOperations = mongoOperations;
//	}
//	
//	@Override
//	public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
//		ReflectionUtils.makeAccessible(field);
//		if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
//			final Object fieldValue = field.get(source);
//			if (fieldValue != null) {
//				FieldCallback callback = new FieldCallback();
//				if (fieldValue instanceof List<?>) {
//					for (Object	object:	(List<?>)fieldValue) {
//						mongoOperations.save(object);
//					}
//				}
//				else {
//					ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
//					mongoOperations.save(fieldValue);
//				}
//			}
//		}
//	}	
//
//}
