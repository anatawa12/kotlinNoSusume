package com.anatawa12.infraWorkshop.kotlinNoSusume.nullSafety;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by anatawa12 on 2018/06/24.
 */
@SuppressWarnings("NullableProblems")
public interface ObjectMethodsCaller {
	boolean nullableEquals(@Nullable Object o1, @Nullable Object o2);
	boolean nonNullableEquals(@NotNull Object o1, @NotNull Object o2);
	int nullableHashCode(@Nullable Object o1);
	int nonNullableHashCode(@NotNull Object o1);
	String nullableToString(@Nullable Object o1);
	String nonNullableToString(@NotNull Object o1);
}
