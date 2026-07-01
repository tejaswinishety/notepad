package de.zeroco.notepad.util;

import java.util.Collection;
import java.util.Map;

public class Util {
	
	public static boolean isBlank(Object input) {
		if (input == null)
			return true;
		if ((input instanceof Integer)) {
			if ((int) input <= 0)
				return true;
		} else if (input instanceof Long) {
			if ((long) input <= 0)
				return true;
		} else if (input instanceof Float) {
			if ((float) input <= 0.0f)
				return true;
		} else if (input instanceof Double) {
			if ((double) input <= 0.0)
				return true;
		} else if (input instanceof String) {
			if ((((String) input).trim().isEmpty()))
				return true;
		} else if ((input instanceof Character)) {
			if (Character.isWhitespace((Character) input))
				return true;
		} else if (input.getClass().isArray()) {
			if (java.lang.reflect.Array.getLength(input) == 0)
				return true;
		}  else if (input instanceof Collection<?>) {
			if (((Collection<?>) input).isEmpty())
				return true;
		} else if  (input instanceof Map<?, ?>) {
			if (((Map<?, ?>) input).isEmpty())
				return true;
		}
		return false;
	}
	
	public static boolean hasData(Object input) {
		return !isBlank(input);
	}
}