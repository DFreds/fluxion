package com.nigelbrown.fluxion;

import android.support.v4.util.ArrayMap;

/**
 * Created by Nigel.Brown on 8/11/2017.
 */
public class Reaction {
	private final String mType;
	private final ArrayMap<String, Object> mData;

	Reaction(String type, ArrayMap<String, Object> data) {
		this.mType = type;
		this.mData = data;
	}

	public static Builder type(String type) {
		return new Builder().with(type);
	}

	public String getType() {
		return mType;
	}

	public ArrayMap<String, Object> getData() {
		return mData;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String tag) {
		return (T)mData.get(tag);
	}

	@Override
	public int hashCode() {
		int result = mType.hashCode();
		result = 31 * result + (mData != null ? mData.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) { return true; }
		if(! (o instanceof Reaction)) { return false; }
		Reaction reaction = (Reaction)o;
		if(! mType.equals(reaction.mType)) { return false; }
		return ! (mData != null ? ! mData.equals(reaction.mData) : reaction.mData != null);
	}

	@Override
	public String toString() {
		return "FluxAction{" + "mType='" + mType + '\'' + ", mData=" + mData + '}';
	}

	public static class Builder {
		private String type;
		private ArrayMap<String, Object> data;

		Builder with(String type) {
			if(type == null) {
				throw new IllegalArgumentException("Type may not be null.");
			}
			this.type = type;
			this.data = new ArrayMap<>();
			return this;
		}

		public Builder bundle(String key, Object value) {
			if(key == null) {
				throw new IllegalArgumentException("Key may not be null.");
			}
			if(value == null) {
				throw new IllegalArgumentException("Value may not be null.");
			}
			data.put(key, value);
			return this;
		}

		public Reaction build() {
			if(type == null || type.isEmpty()) {
				throw new IllegalArgumentException("At least one key is required.");
			}
			return new Reaction(type, data);
		}
	}
}
