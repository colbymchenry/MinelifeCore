package com.minelife.core.util;


import java.lang.annotation.Retention;

import java.lang.annotation.Target;



import static java.lang.annotation.ElementType.METHOD;

import static java.lang.annotation.RetentionPolicy.RUNTIME;



/**

 * Methods annotated by this will be checked if they override a method with the same description as them,

 * which was injected to the parent class via ASM manipulation.

 */

@Retention(value = RUNTIME)

@Target(value = METHOD)

public @interface ASMOverride

{

    String value();

}