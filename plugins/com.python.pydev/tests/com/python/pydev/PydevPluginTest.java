package com.python.pydev;

import com.python.pydev.PydevPlugin.InvalidLicenseException;

import junit.framework.TestCase;

public class PydevPluginTest extends TestCase {

    public void testLicense() throws Exception {
        //aptana
        assertTrue(PydevPlugin.isLicenseValid("79520043668514798408575959688086208501031986726331308027544769712660703698396924832521593790571755962455602257270402379966904551535139947420009243157120299554686516741108230719607209237535720280158799485885133114149307852086270129033389106724350023783276670021257235301374402225857047592865440366560155648253", 
                "0000", true, null));
        
        //aptana expired
        try {
            PydevPlugin.isLicenseValid("22983901328960548200186726172752019755391927862273541221817531927825394897692490551866553975351369337755911292721220479872921968921016134817557812147304787606042260500911113707788278292805637156469732836924511378606211568124640990571941177079604439414795160995140097715439323455782203014983181370468416294241", 
                    "joellelam2", true, null);
            fail("Should raise exception");
        } catch (InvalidLicenseException e) {
            assertEquals("The current Aptana trial license expired at: 2008-10-02", e.getMessage());
        }
        
        //pydev expired
        try {
            PydevPlugin.isLicenseValid("91266625523236875135359847069194674677884746813150106426949255767605007789794635005737572756561092446592266860861897848446415675247550228790703288393961695258818439550280038087352454170768090951618586889663998733868830016833206112201447058584922309367342517004362861586551855771074439727690133413686200847262@",
                    "foo@foo.com", true, null);
            fail("Should raise exception");
        } catch (InvalidLicenseException e) {
            assertEquals("The current license expired at: 2003-09-22", e.getMessage());
        }
        
        //pydev
        assertTrue(PydevPlugin.isLicenseValid("14347264326587699397135285366496832508324696611535879116341671490846385510240089008342164560689184660768758543840319940912085205959373651362742129768754728377452553440777612694396685532444378726181897843471619409144661137750191193462925466255055052418536761275038350532054823966936781389934049016894254906451@@",
                    "foo@foo.com", true, null));
        
        //invalid (some random numbers removed from a valid license)
        try {
            PydevPlugin.isLicenseValid("912666255232687513535984769194674677884746813150106426949255767605007789794635005737572756561092446592266860861897848446415675247550228790703288393961695258818495502800380873245417076809095161858688966399873386883001683320611220144705858492230936734251700436286158655185577107443972769013341368620084726@",
                    "foo@foo.com", true, null);
            fail("Should raise exception");
        } catch (InvalidLicenseException e) {
            assertEquals("The license is not correct, please re-paste it. If this error persists, please request a new license.", e.getMessage());
        }
        
    }
}