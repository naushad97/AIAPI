package com.ehi.enterprise_nextgen_app.services;

/**
 * Created by M97A on 12/13/2017.
 */

import android.annotation.SuppressLint;
import android.os.Build;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Objects;

public class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] actualTypeArguments;
    private final Class<?> rawType;
    private final Type ownerType;

    public ParameterizedTypeImpl(Class<?> arg0, Type[] arg1, Type arg2) {
        this.actualTypeArguments = arg1;
        this.rawType = arg0;
        this.ownerType = (Type) (arg2 != null ? arg2 : arg0.getDeclaringClass());
        this.validateConstructorArguments();
    }

    private void validateConstructorArguments() {
        TypeVariable[] arg0 = this.rawType.getTypeParameters();
        if (arg0.length != this.actualTypeArguments.length) {
            throw new MalformedParameterizedTypeException();
        } else {
            for (int arg1 = 0; arg1 < this.actualTypeArguments.length; ++arg1) {
                ;
            }

        }
    }

    public static ParameterizedTypeImpl make(Class<?> arg, Type[] arg0, Type arg1) {
        return new ParameterizedTypeImpl(arg, arg0, arg1);
    }

    public Type[] getActualTypeArguments() {
        return (Type[]) this.actualTypeArguments.clone();
    }

    public Class<?> getRawType() {
        return this.rawType;
    }

    public Type getOwnerType() {
        return this.ownerType;
    }

    public boolean equals(Object arg0) {
        if (arg0 instanceof ParameterizedType) {
            ParameterizedType arg1 = (ParameterizedType) arg0;
            if (this == arg1) {
                return true;
            } else {
                Type arg2 = arg1.getOwnerType();
                Type arg3 = arg1.getRawType();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    return Objects.equals(this.ownerType, arg2) && Objects.equals(this.rawType, arg3)
                            && Arrays.equals(this.actualTypeArguments, arg1.getActualTypeArguments());
                }
                return false;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Arrays.hashCode(this.actualTypeArguments) ^ Objects.hashCode(this.ownerType)
                    ^ Objects.hashCode(this.rawType);
        } else {
            return Arrays.hashCode(this.actualTypeArguments);
        }

    }

    public String toString() {
        StringBuilder arg0 = new StringBuilder();
        if (this.ownerType != null) {
            if (this.ownerType instanceof Class) {
                arg0.append(((Class) this.ownerType).getName());
            } else {
                arg0.append(this.ownerType.toString());
            }

            arg0.append(".");
            if (this.ownerType instanceof ParameterizedTypeImpl) {
                arg0.append(this.rawType.getName()
                        .replace(((ParameterizedTypeImpl) this.ownerType).rawType.getName() + "$", ""));
            } else {
                arg0.append(this.rawType.getName());
            }
        } else {
            arg0.append(this.rawType.getName());
        }

        if (this.actualTypeArguments != null && this.actualTypeArguments.length > 0) {
            arg0.append("<");
            boolean arg1 = true;
            Type[] arg2 = this.actualTypeArguments;
            int arg3 = arg2.length;

            for (int arg4 = 0; arg4 < arg3; ++arg4) {
                Type arg5 = arg2[arg4];
                if (!arg1) {
                    arg0.append(", ");
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    arg0.append(arg5.getClass().getTypeName());
                }
                arg1 = false;
            }

            arg0.append(">");
        }

        return arg0.toString();
    }
}
