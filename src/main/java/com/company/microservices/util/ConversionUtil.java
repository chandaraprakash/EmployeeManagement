package com.company.microservices.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConversionUtil {

	/**
	 *
	 * @param iterator
	 * @return
	 */
	public static <T> List<T> toArrayList(final Iterator<T> iterator) {
        ArrayList<T> arrayList = new ArrayList<>();
        iterator.forEachRemaining(arrayList::add);
        return arrayList;
    }

}
