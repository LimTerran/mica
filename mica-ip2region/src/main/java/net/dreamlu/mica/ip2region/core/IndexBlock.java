package net.dreamlu.mica.ip2region.core;

import net.dreamlu.mica.ip2region.utils.Ip2regionUtil;

/**
 * item index class
 *
 * @author chenxin<chenxin619315 @ gmail.com>
 */
public class IndexBlock {
	private static final int LENGTH = 12;

	/**
	 * start ip address
	 */
	private long startIp;

	/**
	 * end ip address
	 */
	private long endIp;

	/**
	 * data ptr and data length
	 */
	private int dataPtr;

	/**
	 * data length
	 */
	private int dataLen;

	public IndexBlock(long startIp, long endIp, int dataPtr, int dataLen) {
		this.startIp = startIp;
		this.endIp = endIp;
		this.dataPtr = dataPtr;
		this.dataLen = dataLen;
	}

	public long getStartIp() {
		return startIp;
	}

	public IndexBlock setStartIp(long startIp) {
		this.startIp = startIp;
		return this;
	}

	public long getEndIp() {
		return endIp;
	}

	public IndexBlock setEndIp(long endIp) {
		this.endIp = endIp;
		return this;
	}

	public int getDataPtr() {
		return dataPtr;
	}

	public IndexBlock setDataPtr(int dataPtr) {
		this.dataPtr = dataPtr;
		return this;
	}

	public int getDataLen() {
		return dataLen;
	}

	public IndexBlock setDataLen(int dataLen) {
		this.dataLen = dataLen;
		return this;
	}

	public static int getIndexBlockLength() {
		return LENGTH;
	}

	/**
	 * get the bytes for storage
	 *
	 * @return byte[]
	 */
	public byte[] getBytes() {
		/*
		 * +------------+-----------+-----------+
		 * | 4bytes        | 4bytes    | 4bytes    |
		 * +------------+-----------+-----------+
		 *  start ip      end ip      data ptr + len
		 */
		byte[] b = new byte[12];

		// start ip
		Ip2regionUtil.writeIntLong(b, 0, startIp);
		// end ip
		Ip2regionUtil.writeIntLong(b, 4, endIp);

		// write the data ptr and the length
		long mix = dataPtr | ((dataLen << 24) & 0xFF000000L);
		Ip2regionUtil.writeIntLong(b, 8, mix);

		return b;
	}
}
