package com.taoyuan.gms.common.util;

import java.util.Collection;

public class CollectionsUtil {

	public static boolean isNotEmpty(Collection<?> col) {
		return !(null == col || col.isEmpty());
	}
}
