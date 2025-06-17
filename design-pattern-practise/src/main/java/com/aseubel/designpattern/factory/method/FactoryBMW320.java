package com.aseubel.designpattern.factory.method;

import com.aseubel.designpattern.factory.car.BMW320;

public class FactoryBMW320 implements FactoryBMW{
 
	@Override
	public BMW320 createBMW() {
		return new BMW320();
	}
 
}