package com.ehi.enterprise_nextgen_app.services;

import com.ehi.enterprise_nextgen_app.bo.BaseResponse;

import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by M97A on 12/13/2017.
 */

public class MyParameterizedTypeImpl<T> extends ParameterizedTypeReference<T> {
    Class<T> typeParameterClass;

    public MyParameterizedTypeImpl(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public Type getType() {
        Type[] responseWrapperActualTypes = {typeParameterClass};
        ParameterizedType responseWrapperType = new ParameterizedTypeImpl(
                BaseResponse.class,
                responseWrapperActualTypes,
                null
        );
        return responseWrapperType;
    }
}
