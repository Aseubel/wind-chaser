package com.aseubel.designpattern;

import com.aseubel.designpattern.adapter.MyAdapter.Charger;
import com.aseubel.designpattern.adapter.eobject.Phone;
import com.aseubel.designpattern.adapter.eobject.PhoneWatch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/6/16 下午11:11
 */
@SpringBootTest
public class AdapterTest {

    @Test
    public void testAdapter() {
        System.out.println("Adapter test");
        Charger charger = new Charger();
        Phone xxPhone = new Phone("xxPhone", 50, 22.5);
        PhoneWatch xxWatch = new PhoneWatch("xxWatch", 20, 12.5);
        charger.charge(xxPhone);
        charger.charge(xxWatch);
    }
}
