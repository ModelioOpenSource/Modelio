/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.xmi.util;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;

@objid ("4711f63d-8785-4d1b-8096-f0d7324f94b1")
public class XMIEAnnotation {
    @objid ("22b3d1cd-8ec6-4c86-8961-8e910201fa37")
    public static final String OBJING_NAME = "Objing";

    @objid ("48a17f59-122d-4b5b-a633-b0f562b9e439")
    public static final String TRUE_VALUE = "true";

    @objid ("4387d61d-7686-484c-b656-15cb19d472a6")
    public static final String FALSE_VALUE = "false";

    @objid ("f66ab0af-7895-478f-ae3e-35036c042f9d")
    public static void setOrCreateEAnnotationContent(org.eclipse.uml2.uml.Element ecoreElt, String eaName, String fieldName, String fieldContent) {
        if ((ecoreElt != null) && (fieldContent != null) && (!(fieldContent.equals("")))) {
            // Gets or creates the EAnnotation:
            EAnnotation ea = ecoreElt.getEAnnotation(eaName);
            if (ea == null)
                ea = ecoreElt.createEAnnotation(eaName);
        
            // Gets or creates the Property
            Property prop = null;
            for (Object content : ea.getContents()) {
                if (content instanceof Property) {
                    Property property = (Property) content;
                    if (fieldName.equals(property.getName())) {
                        prop = property;
                        break;
                    }
                }
            }
            if (prop == null) {
                prop = UMLFactory.eINSTANCE.createProperty();
                prop.setName(fieldName);
            }
        
            // Sets the field's content and adds the Property to the
            // EAnnotation:
            prop.setDefault(fieldContent);
            ea.getContents().add(prop);
        }
    }

    @objid ("43202b11-4d06-4895-94c2-b2059f0b97d9")
    protected static String getEAnnotationContent(org.eclipse.uml2.uml.Element ecoreElt, String eaName, String fieldName) {
        String strContent = "";
        if (ecoreElt != null) {
            EAnnotation ea = ecoreElt.getEAnnotation(eaName);
            if (ea != null) {
                for (Object content : ea.getContents()) {
                    if (content instanceof Property) {
                        Property prop = (Property) content;
                        if (fieldName.equals(prop.getName())) {                               
                            strContent = prop.getDefault();    
                            break;
                        }
                    }
                }
            }
        }
        
        if (strContent == null){
            return "";
        }
        return strContent;
    }

    @objid ("64649756-41fa-4459-8510-e640fe41ee8d")
    protected static boolean hasEAnnotationContent(org.eclipse.uml2.uml.Element ecoreElt, String eaName, String fieldName) {
        if (ecoreElt != null) {
            EAnnotation ea = ecoreElt.getEAnnotation(eaName);
            if (ea != null) {
                for (Object content : ea.getContents()) {
                    if (content instanceof Property)
                        if (fieldName.equals(((Property) content).getName()))
                            return true;
                }
            }
        }
        return false;
    }

    @objid ("c60b1948-69ca-40cb-b79d-6649b86b216a")
    protected static EAnnotation getEAnnotation(org.eclipse.uml2.uml.Element ecoreElt, String eaName) {
        if (ecoreElt != null)
            return ecoreElt.getEAnnotation(eaName);
        return null;
    }

    @objid ("1e6deaf6-06e7-4d03-b508-27290911628d")
    protected static EAnnotation createEAnnotation(org.eclipse.uml2.uml.Element ecoreElt, String eaName) {
        EAnnotation ea = null;
        if (ecoreElt != null)
            ea = ecoreElt.createEAnnotation(eaName);
        return ea;
    }

    @objid ("c613cc4b-229b-4065-832a-29e9778a1b02")
    protected static EAnnotation getOrCreateEAnnotation(org.eclipse.uml2.uml.Element ecoreElt, String eaName) {
        EAnnotation ea = null;
        if (ecoreElt != null) {
            // Gets or creates the EAnnotation:
            ea = ecoreElt.getEAnnotation(eaName);
            if (ea == null)
                ea = ecoreElt.createEAnnotation(eaName);
        }
        return ea;
    }

    @objid ("a53c810d-af60-4a0c-948e-681ec71b7c74")
    public static void createEAnnotation(org.eclipse.uml2.uml.Element ecoreElt, String eaName, String fieldName) {
        if (ecoreElt != null) {
            // Gets or creates the EAnnotation:
            EAnnotation ea = ecoreElt.getEAnnotation(eaName);
            if (ea == null)
                ea = ecoreElt.createEAnnotation(eaName);
        
            // Gets or creates the Property:
            Property prop = null;
            for (Object content : ea.getContents()) {
                if (content instanceof Property) {
                    Property property = (Property) content;
                    if (fieldName.equals(property.getName())) {
                        prop = property;
                        break;
                    }
                }
            }
            if (prop == null) {
                prop = UMLFactory.eINSTANCE.createProperty();
                prop.setName(fieldName);
            }
        
            ea.getContents().add(prop);
        }
    }

    @objid ("600e457f-e0b9-412a-b1aa-b96113cf2631")
    public static void deleteEAnnotation(org.eclipse.uml2.uml.Element ecoreElt, String eaName, String fieldName) {
        if (ecoreElt != null) {
            // Gets or creates the EAnnotation:
            EAnnotation ea = ecoreElt.getEAnnotation(eaName);
            if (ea != null){
        
                Property field = null;
                for (Object content : ea.getContents()) {
                    if (content instanceof Property) {
                        Property prop = (Property) content;
                        if (fieldName.equals(prop.getName())) {
                            field = prop;
                            break;
                        }
                    }
                }
        
                if (field != null){
                    ea.getContents().remove(field);
                    if (ea.getContents().size() == 0){
                        ecoreElt.getEAnnotations().remove(ea);
                    }
                }
        
            }
        
        }
    }

    @objid ("21371758-af13-4138-8b64-31c194d3b813")
    protected static String getAndDestroyEAnnotationContent(org.eclipse.uml2.uml.Element ecoreElt, String eaName, String fieldName) {
        String strContent = "";
        if (ecoreElt != null) {
            EAnnotation ea = ecoreElt.getEAnnotation(eaName);
            if (ea != null) {
                for (Object content : ea.getContents()) {
                    if (content instanceof Property) {
                        Property prop = (Property) content;
                        if (fieldName.equals(prop.getName())) {
        
                            strContent = prop.getDefault();    
                            ea.getContents().remove(prop);
                            prop.destroy();
                            break;
                        }
                    }
                }
            }
        }
        
        if (strContent == null){
            return "";
        }
        return strContent;
    }

    @objid ("a981efd4-5bf7-4cfa-91a5-b70b60fae8b7")
    public static void addEAnnotationContent(final org.eclipse.uml2.uml.Element ecoreElt, final String eaName, final String fieldName, final String fieldContent) {
        if ((ecoreElt != null) && (fieldContent != null) && (!(fieldContent.equals("")))) {
            // Gets or creates the EAnnotation:
            EAnnotation ea = ecoreElt.getEAnnotation(eaName);
            if (ea == null)
                ea = ecoreElt.createEAnnotation(eaName);
        
            // Gets or creates the Property:
            Property prop = null;
            for (Object content : ea.getContents()) {
                if (content instanceof Property) {
                    Property property = (Property) content;
                    if (fieldName.equals(property.getName()) && fieldContent.equals(property.getDefault())) {
                        prop = property;
                        break;
                    }
                }
            }
        
            if (prop == null) {
                prop = UMLFactory.eINSTANCE.createProperty();
                prop.setName(fieldName);
        
                // Sets the field's content and adds the Property to the
                // EAnnotation:
                prop.setDefault(fieldContent);
                ea.getContents().add(prop);
            }
        
        }
    }

    @objid ("fb4cc62c-1bfa-459a-b845-09f79a6ae83a")
    protected static List<String> getEAnnotationContents(final org.eclipse.uml2.uml.Element ecoreElt, final String eaName, final String fieldName) {
        List<String> strContent = new ArrayList<>();
        if (ecoreElt != null) {
            EAnnotation ea = ecoreElt.getEAnnotation(eaName);
            if (ea != null) {
                for (Object content : ea.getContents()) {
                    if (content instanceof Property) {
                        Property prop = (Property) content;
                        if (fieldName.equals(prop.getName())) {                               
                            strContent.add(prop.getDefault());    
                        }
                    }
                }
            }
        }
        return strContent;
    }

    @objid ("fc7d2efd-e037-476f-a785-212a0724a22c")
    public static boolean getBooleanContent(final org.eclipse.uml2.uml.Element ecoreElt, final String annotation) {
        if (hasEAnnotationContent(ecoreElt, OBJING_NAME, annotation)){
            return (!(FALSE_VALUE.equals(getEAnnotationContent(ecoreElt, OBJING_NAME, annotation))));
        }
        return false;
    }

}
