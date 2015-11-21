package SAM.SamModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.MyWriter;

// SAM instrument suite

public class SAM {

	private final static boolean ON = true;
	private final static boolean OFF = false;
	private boolean state;
	private static int sol = 1;
	JSONObject expData;

	public SAM() {
		state = ON;
		expData = new JSONObject();
		++sol;
	}

	// Turning on the SAM instrument suite for experiment
	public void turnOn() {
		state = ON;
		System.out.println("SAM Module: SAM is turning on");
	}

	// turning off the instrument suite
	public void turnOff() {
		state = OFF;
		System.out.println("SAM Module: SAM is turning off");
	}

	public boolean isOn() {
		return state;
	}

	public void pyrolysis() throws InterruptedException {
		// Seq #4
		// analysis for GASES EVOLVED FROM SAMPLES as a function of temperature
		String instrument = "GCMS";
		String unit = "million counts/second time";
		ArrayList<Double> values = new ArrayList<Double>();
		System.out.println();
		System.out.println("Detecting organic counts in the sample");
		for (int i = 0; i < 50; i++) {
			values.add((Math.random() * (0.5)));
		}
		Thread.sleep(1000);
		System.out.println("Writing to the data set");
		System.out.println();

		JSONObject pyrolysisAnalysis = new JSONObject();
		pyrolysisAnalysis.put("Intrument", instrument);
		pyrolysisAnalysis.put("unit", unit);
		pyrolysisAnalysis.put("values", values);
		pyrolysisAnalysis.put("sol", sol);
		expData.put("Pyrolysis Experiment [Exp Seq 4]", pyrolysisAnalysis);

	}

	public void derivatization() throws InterruptedException {
		// Seq #2
		// analysis for derivatized polar compounds as a function of temperature

		String relInt = "Relative Intensity";
		String relTime = "Retention Time (min)";
		int time = 8;
		ArrayList<Double> values = new ArrayList<Double>();
		ArrayList<Integer> mins = new ArrayList<Integer>();
		System.out.println();
		System.out.println("DERIVATIZING AMINO ACIDS WITH A REAGENT N,N-Methyl-tert.-butyl");
		Thread.sleep(1000);
		System.out.println("Calculating relative intensity:");
		System.out.println();
		for (int i = 0; i < 25; i++) {
			double value = (Math.random() * 1000000);
			values.add(value);
			mins.add(time++);
		}
		Thread.sleep(1000);
		System.out.println("Writing to the data set");
		System.out.println();

		JSONObject derivatization = new JSONObject();
		derivatization.put(relInt, values);
		derivatization.put(relTime, mins);
		derivatization.put("sol", sol);
		expData.put("Derivatization Experiment [Exp Seq 2]", derivatization);

	}

	public void combustion() throws InterruptedException, IOException, ParseException {
		// Seq #8
		// ANALYSIS OF CARBON ISOTOPES IN CO2 PRODUCED BY COMBUSTION IN OXYGEN
		String cps = "cps (dynamic viscosity)";
		String temp = "Sample Temperature (deg C)";
		JSONArray values = new JSONArray();
		JSONArray temps = new JSONArray();
		System.out.println();
		System.out.println("Performing EGA analysis on the sample");
		double dummyTemp = 10;
		for (int i = 0; i < 100; i++) {
			double value = (Math.random() * 1000);
			;
			dummyTemp = dummyTemp + 10;
			values.add(value);
			temps.add(dummyTemp);
		}
		Thread.sleep(1000);
		System.out.println("Writing to the data set");
		System.out.println();

		JSONObject combustion = new JSONObject();
		combustion.put(cps, values);
		combustion.put(temp, temps);
		combustion.put("sol", sol);
		expData.put("Combustion Experiment [Exp Seq 8]", combustion);

	}

	public void atmosphericMeasurement() throws InterruptedException {
		// Seq #4
		// QMS and TLS analysis of atmospheric chemicals and isotopic
		// composition
		System.out.println();
		System.out.println("Detecting gases from atmosphere");
		System.out.println();
		System.out.println("Calculating abundance from the sampled gases");
		JSONObject gasObjects = new JSONObject();
		String gases[] = { "CO2", "Ar", "N2", "O2", "CO" };
		for (int i = 0; i < 5; i++) {
			double value = Math.random();
			JSONObject atmGas = new JSONObject();
			atmGas.put("Volum mixing ratio", value);
			atmGas.put("sol", sol);
			gasObjects.put(gases[i], atmGas);
		}
		Thread.sleep(1000);
		System.out.println("Writing to the data set");
		System.out.println();
		expData.put("Direct-Atmospheric-Measurement Experiment", gasObjects);
	}

	public void atmosphericEnrichment() throws InterruptedException {
		// Seq #5
		// QMS, TLS, and GCMS analysis of stmospheric trace species
		System.out.println();
		System.out.println("Performing enirhcment experiment of the sampled gas");
		JSONObject atmEnrich = new JSONObject();
		JSONArray values = new JSONArray();
		JSONArray mzs = new JSONArray();
		int mz = 10;
		for (int i = 0; i < 20; i++) {
			int counts = (int) (1000 + Math.random() * 1000000);
			mz++;
			values.add(counts);
			mzs.add(mz);
		}
		Thread.sleep(1000);
		System.out.println("Writing to the data set");
		atmEnrich.put("sol", sol);
		atmEnrich.put("Count rate (counts/s)", values);
		atmEnrich.put("m/z", mzs);
		System.out.println();
		expData.put("Atmospheric-Enrichment Experiment", atmEnrich);

	}

	public void methaneEnrichment() throws InterruptedException {
		// Seq #6
		// TLS methane analysis
		double value = Math.random() * 2;
		JSONObject methane = new JSONObject();
		System.out.println();
		System.out.println("Calculating abundance from the sampled gas");
		Thread.sleep(1000);
		System.out.println("Writing to the data set");
		System.out.println();
		methane.put("value", value);
		methane.put("unit", "ppbv");
		methane.put("sol", sol);
		expData.put("metahne-Enrichment Experiment", methane);

	}

	public void nobleGasEnrichment() throws InterruptedException {
		// Seq #6
		// Noble gas analysis with SAM CSPL in QMS noble enrichment
		JSONObject nobleGas = new JSONObject();
		ArrayList<Double> xe = new ArrayList<Double>();
		System.out.println();
		System.out.println("Performing enrichment cycles for the noble gas sample");
		Thread.sleep(1000);
		for (int i = 0; i < 100; i++) {
			double value = 100 + Math.random() * 1000;
			;
			xe.add(value);
		}
		System.out.println("Writing to the data set");
		System.out.println();
		nobleGas.put("sol", sol);
		nobleGas.put("QMS Intensity (cps)", xe);
		expData.put("Noble-Gas-Enrichment Experiment", nobleGas);

	}

	public void inSituGasCalibration() {
		// Seq #9
		// Calibrating QMS, TLS and GCMS to check instrument performance and
		// changes with time
		System.out.println();
		System.out.println("Calibrating for Direct QMS Atmospheric Measurements");
		System.out.println();
	}

	public void solidSampleInSituCalibration() {
		// Seq #3
		// chemical and isotopic analysis with internal calibration standards
		System.out.println();
		System.out.println("Calibration for Evolved Gas Analysis of Solid Samples");
		System.out.println();
	}

	public String getPowerConsumption() {
		// TODO Auto-generated method stub
		return null;
	}

}
