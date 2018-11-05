package com.taoyuan.gms.common.util;

import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class CollectionsUtil {

	public static boolean isNotEmpty(Collection<?> col) {
		return CollectionUtils.isEmpty(col);
	}
}
