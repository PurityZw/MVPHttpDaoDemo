package com.bestvike.mvphttpdaodemo.bean;

/**
 **********************
 * ResultBean.java
 * package com.example.ok;
 * com.example.ok
 * 
 * hqx
 * public class ResultBean{ }
 * ResultBean
 *************************
 */
public class ResultBean {
	public HeadBean head;
	public Object body;
	
	public HeadBean getHead() {
		return head;
	}
	public void setHead(HeadBean head) {
		this.head = head;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}

}
