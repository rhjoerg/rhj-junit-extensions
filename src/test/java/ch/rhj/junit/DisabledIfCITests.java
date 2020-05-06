package ch.rhj.junit;

import org.junit.jupiter.api.Test;

public class DisabledIfCITests {

	@Test
	public void test1() {

		System.out.println("test 1 executed as expected");
	}

	@Test
	@DisabledIfCI
	public void test2() {

		System.out.println("test 2 executed, not running on travis");
	}
}
