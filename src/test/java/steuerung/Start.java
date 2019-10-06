package steuerung;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Start {

	public static void main(String[] args) {

		TestNG testng = new TestNG();

		// testng.setTestClasses(new Class[] { testklassen.Playgroundtest01.class });

		// Instanziierung einer TestSuite
		XmlSuite suite = new XmlSuite();
		suite.setName("mySuite");

		// Instanziierung eines Tests als Bestandteil einer Testsuite wo Testklassen
		// oder Packages drin liegen können
		XmlTest test = new XmlTest(suite);
		test.setName("myTestInnerhalbEinerTestsuite");
		
		// Instanziierung eines weiteren Tests als Bestandteil einer Testsuite wo Testklassen
		// oder Packages drin liegen können
		XmlTest neuerTest = new XmlTest(suite);
		neuerTest.setName("mySecondTestInnerhalbEinerTestsuite");
		
		// Instanziierung eines Packages als Bestandteil eines Tests einer Testsuite
		List<XmlPackage> eingeschlossenepackages = new ArrayList<XmlPackage>();
		eingeschlossenepackages.add(new XmlPackage("besondereTestklassen"));

		// Hinzufügen des Packages mit der Testklasse zu dem Test in der Testsuite
		test.setPackages(eingeschlossenepackages);

		// Hinzufügen des Packages mit der Testklasse zu dem zweiten Test in der Testsuite
		neuerTest.setPackages(eingeschlossenepackages);

		// Instanziierung einer Liste mit Testklassen die einem Test zugeordnet werden
		// können
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("testklassen.Playgroundtest01"));
		classes.add(new XmlClass("testklassen.Playgroundtest02"));
		classes.add(new XmlClass("testklassen.Playgroundtest03"));

		// Dem Testabschnitt innerhalb der Testsuite werden jetzt hier die Testklassen
		// zugeordnet
		test.setXmlClasses(classes);

		// Instanziierung einer Collection von Testsuites die dann per TestNG Runner
		// ausgeführt werden
		List<XmlSuite> suites = new ArrayList<XmlSuite>();

		// Instanziierung eines Liste mit eigenen Listenern
		List<Class<? extends ITestNGListener>> myITestNGListeners = new ArrayList<Class<? extends ITestNGListener>>();
		myITestNGListeners.add(listener.MyITestListener.class);

		// Instanziierung eines Liste mit eigenen Listenern
		List<Class<? extends ITestResult>> myITestResultsListeners = new ArrayList<Class<? extends ITestResult>>();
		myITestResultsListeners.add(listener.MyITestResult.class);

		// Zuordnung der Testsuite die wir oben angelegt haben
		suites.add(suite);

		// Die auszuführenden Testsuiten werden an TestNG übergeben
		testng.setXmlSuites(suites);
		testng.setListenerClasses(myITestNGListeners);
//		testng.setListenerClasses(myITestResultsListeners);
		
		testng.run();

	}

}
