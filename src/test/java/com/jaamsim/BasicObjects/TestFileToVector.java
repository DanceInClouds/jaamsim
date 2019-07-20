/*
 * JaamSim Discrete Event Simulation
 * Copyright (C) 2019 JaamSim Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jaamsim.BasicObjects;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jaamsim.ProcessFlow.Server;
import com.jaamsim.basicsim.JaamSimModel;
import com.jaamsim.basicsim.Simulation;
import com.jaamsim.input.ExpResult;
import com.jaamsim.input.InputAgent;

public class TestFileToVector {

	JaamSimModel simModel;

	@Before
	public void setupTests() {
		simModel = new JaamSimModel();
		simModel.createInstance(Simulation.class);
	}

	@Test
	public void testValue() {
		FileToVector fileToVector = InputAgent.defineEntityWithUniqueName(simModel, FileToVector.class, "FileToVector1", "", true);
		Double x = 1.5d;
		String str = "abc";
		Server ent = InputAgent.defineEntityWithUniqueName(simModel, Server.class, "Server1", "", true);
		ArrayList<Double> list = new ArrayList<>();
		list.add(2.5d);
		list.add(3.5d);

		ArrayList<Object> data = new ArrayList<>();
		data.add(x);
		data.add(str);
		data.add(ent);
		data.add(list);
		try {
			fileToVector.setValue(data);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ArrayList<ExpResult> val = fileToVector.getOutputHandle("Value").getValue(0.0d, ArrayList.class);
		//System.out.println(val);
		assertTrue( val.get(0).value == 1.5d );
		assertTrue( val.get(1).stringVal.equals(str) );
		assertTrue( val.get(2).entVal == ent );
		assertTrue( val.get(3).colVal.getSize() == 2 );
	}

}
