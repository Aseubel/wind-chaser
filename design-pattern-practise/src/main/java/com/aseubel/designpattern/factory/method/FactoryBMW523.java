package com.aseubel.designpattern.factory.method;

import com.aseubel.designpattern.factory.car.BMW523;

public class FactoryBMW523 implements FactoryBMW {
	@Override
	public BMW523 createBMW() {
		return new BMW523();
	}
}