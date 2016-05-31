package com.study.spring.core.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import com.study.spring.core.converter.domain.Course;
import com.study.spring.core.converter.domain.User;

/**
 * GenericConverter可以实现多组对象之间的转换
 */
public class ObjectToStringConverter implements GenericConverter {

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> sets = new HashSet<ConvertiblePair>();
		sets.add(new ConvertiblePair(User.class, String.class));
		sets.add(new ConvertiblePair(Course.class, String.class));
		return sets;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {
		System.out.println("sourceType:" + sourceType);
		System.out.println("targetType:" + targetType);
		return source.toString();
	}
}
