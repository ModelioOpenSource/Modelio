/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.gproject.data.module.jaxbv2;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.tools.Tool;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.Command;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="ClassPath" type="{}_MultiPathes"/>
 * &lt;element name="Profiles" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Profile" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Stereotype" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;element name="Icon" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Image" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;choice minOccurs="0">
 * &lt;element name="PropertyTable" type="{}_PropertyTableDefinition" minOccurs="0"/>
 * &lt;element name="TagTypes" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;element name="NoteTypes" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="ExternDocumentTypes" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Constraints" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Constraint" type="{}_LinkConstraint" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Extensions" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="metaclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="owner-stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="is-abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="MetaclassReference" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;choice minOccurs="0">
 * &lt;element name="PropertyTable" type="{}_PropertyTableDefinition" minOccurs="0"/>
 * &lt;element name="TagTypes" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/choice>
 * &lt;element name="NoteTypes" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="ExternDocumentTypes" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="metaclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Extensions" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="PropertyTypes" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="PropertyType" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}_PropertyType">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Parameters" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Parameter" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence minOccurs="0">
 * &lt;element name="Enumeration" type="{}_Enumeration" minOccurs="0"/>
 * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="group" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="type">
 * &lt;simpleType>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 * &lt;enumeration value="Boolean"/>
 * &lt;enumeration value="Color"/>
 * &lt;enumeration value="Directory"/>
 * &lt;enumeration value="Enum"/>
 * &lt;enumeration value="File"/>
 * &lt;enumeration value="Integer"/>
 * &lt;enumeration value="Password"/>
 * &lt;enumeration value="String"/>
 * &lt;/restriction>
 * &lt;/simpleType>
 * &lt;/attribute>
 * &lt;attribute name="default-value" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Gui" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Commands" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Command" type="{}_Command" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Tools" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Tool" type="{}_Tool" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="ContextualMenu" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="CommandRef" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}_CommandRef">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="category" default="MODULE">
 * &lt;simpleType>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 * &lt;enumeration value="MODULE"/>
 * &lt;enumeration value="ELEMENT"/>
 * &lt;enumeration value="DIAGRAM"/>
 * &lt;/restriction>
 * &lt;/simpleType>
 * &lt;/attribute>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Diagrams" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="DiagramType" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Palette" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="ToolRef" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}_ToolRef">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="keepBasePalette" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Wizard" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
 * &lt;element name="Handler" type="{}_Handler"/>
 * &lt;/sequence>
 * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Handler" type="{}_Handler"/>
 * &lt;/sequence>
 * &lt;attribute name="base-diagram" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="stereotype" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Matrices">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="MatrixType" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="X">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}_Handler">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Y" type="{}_Handler"/>
 * &lt;element name="Z" type="{}_Handler" minOccurs="0"/>
 * &lt;element name="Val" type="{}_Handler"/>
 * &lt;element name="Wizard" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
 * &lt;element name="Handler" type="{}_Handler"/>
 * &lt;/sequence>
 * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="stereotype" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Views" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="PropertyPage" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="CommandRef" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}_CommandRef">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Dependencies" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Required" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}_Dependency">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Optional" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;extension base="{}_Dependency">
 * &lt;/extension>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Resources" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Styles" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Style" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="DocFiles" type="{}_MultiPathes" minOccurs="0"/>
 * &lt;element name="Macros" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Macro" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="Patterns" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="Pattern" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="DocTemplates" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="DocTemplate" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 * &lt;attribute name="lang" default="en">
 * &lt;simpleType>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 * &lt;enumeration value="en"/>
 * &lt;enumeration value="fr"/>
 * &lt;/restriction>
 * &lt;/simpleType>
 * &lt;/attribute>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;element name="MetamodelFragments" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;sequence>
 * &lt;element name="MetamodelFragment" maxOccurs="unbounded" minOccurs="0">
 * &lt;complexType>
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="vendor" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="vendor-version" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * &lt;/element>
 * &lt;/sequence>
 * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="binaryversion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="schema-level" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" default="3" />
 * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="author" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}string" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@objid ("2cd07ed3-3f30-4865-9742-dfbfc32c1698")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "", propOrder = {
        "classPath",
        "profiles",
        "propertyTypes",
        "parameters",
        "gui",
        "dependencies",
        "resources",
        "metamodelFragments"
})
@XmlRootElement (name = "Module")
public class Jxbv2Module {
    @objid ("537dd283-0c60-4525-a5c8-814c7ba8b66f")
    @XmlAttribute (name = "id", required = true)
    protected String id;

    @objid ("b9585f5b-7607-45da-9975-506c981da5fc")
    @XmlAttribute (name = "class", required = true)
    protected String clazz;

    @objid ("167ec5f4-1822-4f51-bb50-d6610f9bf041")
    @XmlAttribute (name = "binaryversion", required = true)
    protected String binaryversion;

    @objid ("0b9d421b-edb8-454f-8792-cfacf13b2e96")
    @XmlAttribute (name = "version", required = true)
    protected String version;

    @objid ("2fe68c16-3846-4bb9-bd0c-9f05ef3518f3")
    @XmlAttribute (name = "schema-level")
    @XmlSchemaType (name = "unsignedInt")
    protected Long schemaLevel;

    @objid ("f58525bd-2f4c-42d4-810c-df91cea3137d")
    @XmlAttribute (name = "uid")
    protected String uid;

    @objid ("eca7ef26-6b1b-44e5-88f9-4b8fcffff7c1")
    @XmlAttribute (name = "author")
    protected String author;

    @objid ("34dfdf6a-05e5-4d94-9859-dc124319072f")
    @XmlAttribute (name = "image")
    protected String image;

    @objid ("cb32bb3c-0666-49ee-93bc-b402c0c96e83")
    @XmlAttribute (name = "url")
    protected String url;

    @objid ("efe65d7f-90f3-412e-a139-0933a7d86e70")
    @XmlElement (name = "ClassPath", required = true)
    protected Jxbv2MultiPathes classPath;

    @objid ("85007617-87f9-438f-8c96-9b30024928ea")
    @XmlElement (name = "Profiles")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles profiles;

    @objid ("c248fc94-2501-41e1-a8dd-f2c8cf370a23")
    @XmlElement (name = "PropertyTypes")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2PropertyTypes propertyTypes;

    @objid ("f3893920-d35f-4782-bdd1-3d5d38b7d7a0")
    @XmlElement (name = "Parameters")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters parameters;

    @objid ("fd9448f3-c468-4213-8f96-f8d732195c1e")
    @XmlElement (name = "Gui")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui gui;

    @objid ("4b52b09c-d5b5-4ec8-9cca-8f7fdbde817a")
    @XmlElement (name = "Dependencies")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies dependencies;

    @objid ("7af1984e-927a-4c71-911a-e39450dee78e")
    @XmlElement (name = "Resources")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources resources;

    @objid ("fab4cbb2-199e-4374-bd91-1d73683c47e3")
    @XmlElement (name = "MetamodelFragments")
    protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2MetamodelFragments metamodelFragments;

    /**
     * Gets the value of the classPath property.
     * @return possible object is {@link Jxbv2MultiPathes }
     */
    @objid ("ebc399e5-a1d1-43b7-b3d4-5481a4782deb")
    public Jxbv2MultiPathes getClassPath() {
        return this.classPath;
    }

    /**
     * Sets the value of the classPath property.
     * @param value allowed object is {@link Jxbv2MultiPathes }
     */
    @objid ("961158b5-5382-4de9-9b25-e370ec7e2e6f")
    public void setClassPath(Jxbv2MultiPathes value) {
        this.classPath = value;
    }

    /**
     * Gets the value of the profiles property.
     * @return possible object is {@link Jxbv2Module.Jxbv2Profiles }
     */
    @objid ("5d5b6a11-bd61-48ab-84ea-99fa1908939f")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles getProfiles() {
        return this.profiles;
    }

    /**
     * Sets the value of the profiles property.
     * @param value allowed object is {@link Jxbv2Module.Jxbv2Profiles }
     */
    @objid ("2ea9c7a4-5ea2-4e60-b3a3-027180eb5f2a")
    public void setProfiles(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles value) {
        this.profiles = value;
    }

    /**
     * Gets the value of the propertyTypes property.
     * @return possible object is {@link Jxbv2Module.Jxbv2PropertyTypes }
     */
    @objid ("8d8f5104-956e-4176-b662-1d057c40f2ff")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2PropertyTypes getPropertyTypes() {
        return this.propertyTypes;
    }

    /**
     * Sets the value of the propertyTypes property.
     * @param value allowed object is {@link Jxbv2Module.Jxbv2PropertyTypes }
     */
    @objid ("49f2754d-2d81-474d-93cc-403e4b306296")
    public void setPropertyTypes(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2PropertyTypes value) {
        this.propertyTypes = value;
    }

    /**
     * Gets the value of the parameters property.
     * @return possible object is {@link Jxbv2Module.Jxbv2Parameters }
     */
    @objid ("773b482e-bd15-476a-a224-bb4166d9fbc7")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters getParameters() {
        return this.parameters;
    }

    /**
     * Sets the value of the parameters property.
     * @param value allowed object is {@link Jxbv2Module.Jxbv2Parameters }
     */
    @objid ("f6805a11-d892-4c86-8c09-86a58f76ed2d")
    public void setParameters(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the gui property.
     * @return possible object is {@link Jxbv2Module.Jxbv2Gui }
     */
    @objid ("0fe9909d-a369-46d0-8286-05ba9bee6b19")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui getGui() {
        return this.gui;
    }

    /**
     * Sets the value of the gui property.
     * @param value allowed object is {@link Jxbv2Module.Jxbv2Gui }
     */
    @objid ("5d8f1772-bcb0-47e8-bc10-3271f2517efe")
    public void setGui(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui value) {
        this.gui = value;
    }

    /**
     * Gets the value of the dependencies property.
     * @return possible object is {@link Jxbv2Module.Jxbv2Dependencies }
     */
    @objid ("fd44861d-0e30-4c2b-9753-53de6571e1b7")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies getDependencies() {
        return this.dependencies;
    }

    /**
     * Sets the value of the dependencies property.
     * @param value allowed object is {@link Jxbv2Module.Jxbv2Dependencies }
     */
    @objid ("18e967a2-aa0b-48ab-a979-cc3a9794fd78")
    public void setDependencies(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies value) {
        this.dependencies = value;
    }

    /**
     * Gets the value of the resources property.
     * @return possible object is {@link Jxbv2Module.Jxbv2Resources }
     */
    @objid ("267a63f3-0f98-4887-a9b2-da830d332b96")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources getResources() {
        return this.resources;
    }

    /**
     * Sets the value of the resources property.
     * @param value allowed object is {@link Jxbv2Module.Jxbv2Resources }
     */
    @objid ("ae8ff95c-f201-4073-828d-9654cdb9bdde")
    public void setResources(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources value) {
        this.resources = value;
    }

    /**
     * Gets the value of the id property.
     * @return possible object is {@link String }
     */
    @objid ("3f07c605-32e4-4d21-8bb0-d892a18513f5")
    public String getId() {
        return this.id;
    }

    /**
     * Sets the value of the id property.
     * @param value allowed object is {@link String }
     */
    @objid ("4fd6dc08-98d2-40c7-bfc8-ba7372049739")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the clazz property.
     * @return possible object is {@link String }
     */
    @objid ("96ba2cf9-ab2b-4881-8b9d-530da83c599d")
    public String getClazz() {
        return this.clazz;
    }

    /**
     * Sets the value of the clazz property.
     * @param value allowed object is {@link String }
     */
    @objid ("d39fa14b-9173-4fca-947d-c6069be67271")
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the binaryversion property.
     * @return possible object is {@link String }
     */
    @objid ("7c456dbc-d4dd-4e3e-919e-175f9ce582f0")
    public String getBinaryversion() {
        return this.binaryversion;
    }

    /**
     * Sets the value of the binaryversion property.
     * @param value allowed object is {@link String }
     */
    @objid ("e716b930-4a01-4d7b-9bda-bce1b15dccdd")
    public void setBinaryversion(String value) {
        this.binaryversion = value;
    }

    /**
     * Gets the value of the version property.
     * @return possible object is {@link String }
     */
    @objid ("18a64666-b60c-46d6-8c67-993b0dc18483")
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the value of the version property.
     * @param value allowed object is {@link String }
     */
    @objid ("c97804a4-59b4-486d-8bdf-b7bbcfb705a0")
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the schemaLevel property.
     * @return possible object is {@link Long }
     */
    @objid ("76b9bf25-d4ef-4d9d-a84f-62139c746fe3")
    public long getSchemaLevel() {
        if (this.schemaLevel == null) {
            return 3L;
        } else {
            return this.schemaLevel;
        }
    }

    /**
     * Sets the value of the schemaLevel property.
     * @param value allowed object is {@link Long }
     */
    @objid ("dca9a920-2780-491a-b5d0-f3833b12c320")
    public void setSchemaLevel(Long value) {
        this.schemaLevel = value;
    }

    /**
     * Gets the value of the uid property.
     * @return possible object is {@link String }
     */
    @objid ("00a5605a-161d-46c3-9063-a94c1c966117")
    public String getUid() {
        return this.uid;
    }

    /**
     * Sets the value of the uid property.
     * @param value allowed object is {@link String }
     */
    @objid ("ba02a321-2b90-4801-b5c2-aa7571d71ddc")
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the author property.
     * @return possible object is {@link String }
     */
    @objid ("f6f259cb-7bc8-4975-ac84-79d66c4eb169")
    public String getAuthor() {
        return this.author;
    }

    /**
     * Sets the value of the author property.
     * @param value allowed object is {@link String }
     */
    @objid ("8da30625-4733-4d09-b245-bd3c27899c49")
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the image property.
     * @return possible object is {@link String }
     */
    @objid ("2859dbd7-46a9-4daf-9f98-21ae962b8ed6")
    public String getImage() {
        return this.image;
    }

    /**
     * Sets the value of the image property.
     * @param value allowed object is {@link String }
     */
    @objid ("b147212c-15ab-4fa8-abb7-781ece9e0d7b")
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the url property.
     * @return possible object is {@link String }
     */
    @objid ("3f8d23e0-9a85-401c-834d-d02ac6254cec")
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets the value of the url property.
     * @param value allowed object is {@link String }
     */
    @objid ("7e344c02-9c3e-403e-a72c-f2467951c819")
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the metamodelFragments property.
     * @return possible object is {@link Jxbv2Module.Jxbv2MetamodelFragments }
     */
    @objid ("e9bf567f-12f0-4ff3-aa54-f95423c63a4f")
    public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2MetamodelFragments getMetamodelFragments() {
        return this.metamodelFragments;
    }

    /**
     * Sets the value of the metamodelFragments property.
     * @param value allowed object is {@link Jxbv2Module.Jxbv2MetamodelFragments }
     */
    @objid ("da98cacf-a9a9-4518-901e-89043248a583")
    public void setMetamodelFragments(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2MetamodelFragments value) {
        this.metamodelFragments = value;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Required" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}_Dependency">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Optional" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}_Dependency">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("d304d04f-5fcc-4b95-96df-a37c3fbd7b8f")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "", propOrder = {
            "required",
            "optional"
    })
    public static class Jxbv2Dependencies {
        @objid ("8388eca3-0a7e-447d-81d8-b2ccf010fcf5")
        @XmlElement (name = "Required")
        protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Required> required;

        @objid ("5cbfbbe5-6155-448a-9602-46de93286245")
        @XmlElement (name = "Optional")
        protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Optional> optional;

        /**
         * Gets the value of the required property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the required
         * property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getRequired().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Jxbv2Module.Jxbv2Dependencies.Jxbv2Required }
         */
        @objid ("cfac51a4-40c9-41a5-af8a-818696982fb1")
        public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Required> getRequired() {
            if (this.required == null) {
                this.required = new ArrayList<>();
            }
            return this.required;
        }

        /**
         * Gets the value of the optional property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the optional
         * property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getOptional().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Jxbv2Module.Jxbv2Dependencies.Jxbv2Optional }
         */
        @objid ("0be4d4f0-6f91-4214-9d46-70c1398aa0bc")
        public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Dependencies.Jxbv2Optional> getOptional() {
            if (this.optional == null) {
                this.optional = new ArrayList<>();
            }
            return this.optional;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;extension base="{}_Dependency">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("c3e74bf8-338f-4441-ba33-e94d0dd389bf")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "")
        public static class Jxbv2Optional extends Jxbv2Dependency {
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;extension base="{}_Dependency">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("e805106b-250f-4e13-9ef6-61d3f4fb4aa6")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "")
        public static class Jxbv2Required extends Jxbv2Dependency {
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Commands" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Command" type="{}_Command" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Tools" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Tool" type="{}_Tool" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="ContextualMenu" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="CommandRef" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}_CommandRef">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;attribute name="category" default="MODULE">
     * &lt;simpleType>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     * &lt;enumeration value="MODULE"/>
     * &lt;enumeration value="ELEMENT"/>
     * &lt;enumeration value="DIAGRAM"/>
     * &lt;/restriction>
     * &lt;/simpleType>
     * &lt;/attribute>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Diagrams" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="DiagramType" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Palette" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="ToolRef" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}_ToolRef">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;attribute name="keepBasePalette" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Wizard" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
     * &lt;element name="Handler" type="{}_Handler"/>
     * &lt;/sequence>
     * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Handler" type="{}_Handler"/>
     * &lt;/sequence>
     * &lt;attribute name="base-diagram" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="stereotype" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Matrices">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="MatrixType" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="X">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}_Handler">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Y" type="{}_Handler"/>
     * &lt;element name="Z" type="{}_Handler" minOccurs="0"/>
     * &lt;element name="Val" type="{}_Handler"/>
     * &lt;element name="Wizard" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
     * &lt;element name="Handler" type="{}_Handler"/>
     * &lt;/sequence>
     * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="stereotype" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Views" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="PropertyPage" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="CommandRef" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}_CommandRef">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("59266f76-2516-4572-b2de-bef4f459d6f0")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "", propOrder = {
            "commands",
            "tools",
            "contextualMenu",
            "diagrams",
            "matrices",
            "views"
    })
    public static class Jxbv2Gui {
        @objid ("65544dac-0131-4522-a533-94fe944be167")
        @XmlElement (name = "Commands")
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Commands commands;

        @objid ("179cd360-085e-49bb-9223-b0759d8c61f6")
        @XmlElement (name = "Tools")
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Tools tools;

        @objid ("f504f082-8459-4d74-b781-d090e87fcd2d")
        @XmlElement (name = "ContextualMenu")
        protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu> contextualMenu;

        @objid ("d1421bc3-d611-47a6-902c-4bc70cff0167")
        @XmlElement (name = "Diagrams")
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams diagrams;

        @objid ("d6ed579b-a69a-4b2c-b29d-89020f64ff56")
        @XmlElement (name = "Views")
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views views;

        @objid ("4647d09a-60e2-4405-b095-da708083864a")
        @XmlElement (name = "Matrices", required = true)
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices matrices;

        /**
         * Gets the value of the commands property.
         * @return possible object is {@link Jxbv2Module.Jxbv2Gui.Jxbv2Commands }
         */
        @objid ("3e3bca8e-4f63-4587-bdca-dbb112c70cd2")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Commands getCommands() {
            return this.commands;
        }

        /**
         * Sets the value of the commands property.
         * @param value allowed object is {@link Jxbv2Module.Jxbv2Gui.Jxbv2Commands }
         */
        @objid ("286151cd-a72f-4942-a6b1-10114ffe2570")
        public void setCommands(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Commands value) {
            this.commands = value;
        }

        /**
         * Gets the value of the tools property.
         * @return possible object is {@link Jxbv2Module.Jxbv2Gui.Jxbv2Tools }
         */
        @objid ("3ca80cc8-323d-473b-a799-fb5f0a4b9ceb")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Tools getTools() {
            return this.tools;
        }

        /**
         * Sets the value of the tools property.
         * @param value allowed object is {@link Jxbv2Module.Jxbv2Gui.Jxbv2Tools }
         */
        @objid ("aa920288-38ac-4d22-bbe0-693e942d6b4e")
        public void setTools(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Tools value) {
            this.tools = value;
        }

        /**
         * Gets the value of the contextualMenu property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the contextualMenu
         * property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getContextualMenu().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu }
         */
        @objid ("a4873e42-5905-4a25-b6ff-83f4002bc480")
        public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu> getContextualMenu() {
            if (this.contextualMenu == null) {
                this.contextualMenu = new ArrayList<>();
            }
            return this.contextualMenu;
        }

        /**
         * Gets the value of the diagrams property.
         * @return possible object is {@link Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams }
         */
        @objid ("3376cd3f-f8b4-4e74-bbc0-c56f508882fa")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams getDiagrams() {
            return this.diagrams;
        }

        /**
         * Sets the value of the diagrams property.
         * @param value allowed object is {@link Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams }
         */
        @objid ("18e429d3-7425-466d-b115-606fbc7a0732")
        public void setDiagrams(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams value) {
            this.diagrams = value;
        }

        /**
         * Gets the value of the views property.
         * @return possible object is {@link Jxbv2Module.Jxbv2Gui.Jxbv2Views }
         */
        @objid ("bca094da-e68c-4769-8b11-f268e7516c84")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views getViews() {
            return this.views;
        }

        /**
         * Sets the value of the views property.
         * @param value allowed object is {@link Jxbv2Module.Jxbv2Gui.Jxbv2Views }
         */
        @objid ("a30b875b-dd87-4afa-8d4a-700876acde40")
        public void setViews(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views value) {
            this.views = value;
        }

        /**
         * Gets the value of the matrices property.
         * @return possible object is {@link Module.Gui.Matrices }
         */
        @objid ("baeaa164-b591-415d-ab31-60919e34c1ae")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices getMatrices() {
            return this.matrices;
        }

        /**
         * Sets the value of the matrices property.
         * @param value allowed object is {@link Module.Gui.Matrices }
         */
        @objid ("667993e1-76ce-4e00-a155-fcba09945cfe")
        public void setMatrices(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices value) {
            this.matrices = value;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Command" type="{}_Command" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("001ea32b-c7c2-4e88-8d26-17e82283992e")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "command"
        })
        public static class Jxbv2Commands {
            @objid ("604e661b-dd81-49f4-b587-a06d26b4207b")
            @XmlElement (name = "Command")
            protected List<Jxbv2Command> command;

            /**
             * Gets the value of the command property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the command
             * property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getCommand().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Command }
             */
            @objid ("ec655888-4f80-465c-987d-bfab496700e6")
            public List<Jxbv2Command> getCommand() {
                if (this.command == null) {
                    this.command = new ArrayList<>();
                }
                return this.command;
            }

        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="CommandRef" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;extension base="{}_CommandRef">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;attribute name="category" default="MODULE">
         * &lt;simpleType>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         * &lt;enumeration value="MODULE"/>
         * &lt;enumeration value="ELEMENT"/>
         * &lt;enumeration value="DIAGRAM"/>
         * &lt;/restriction>
         * &lt;/simpleType>
         * &lt;/attribute>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("b321be24-2180-44bd-a65a-094cf86154e7")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "commandRef"
        })
        public static class Jxbv2ContextualMenu {
            @objid ("579e8ed9-b61f-4c50-bce6-dd834612e66a")
            @XmlAttribute (name = "category")
            protected String category;

            @objid ("5e1af69d-ca75-49df-9d99-2c48ca3249c9")
            @XmlElement (name = "CommandRef")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu.Jxbv2CommandRef> commandRef;

            /**
             * Gets the value of the commandRef property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the commandRef
             * property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getCommandRef().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Module.Gui.ContextualMenu.CommandRef }
             */
            @objid ("0cd974c8-fd85-4950-8811-d784389c8a39")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu.Jxbv2CommandRef> getCommandRef() {
                if (this.commandRef == null) {
                    this.commandRef = new ArrayList<>();
                }
                return this.commandRef;
            }

            /**
             * Gets the value of the category property.
             * @return possible object is {@link String }
             */
            @objid ("fb650862-3579-4585-bc73-56ecd9bc59fd")
            public String getCategory() {
                if (this.category == null) {
                    return "MODULE";
                } else {
                    return this.category;
                }
            }

            /**
             * Sets the value of the category property.
             * @param value allowed object is {@link String }
             */
            @objid ("7ef2047f-f8d4-44aa-814b-e053e34fee33")
            public void setCategory(String value) {
                this.category = value;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;extension base="{}_CommandRef">
             * &lt;/extension>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("35100a73-6068-4e88-b344-a7aa97d01bb5")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "")
            public static class Jxbv2CommandRef extends org.modelio.gproject.data.module.jaxbv2.Jxbv2CommandRef {
            }

        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="DiagramType" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Palette" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="ToolRef" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;extension base="{}_ToolRef">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;attribute name="keepBasePalette" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="Wizard" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
         * &lt;element name="Handler" type="{}_Handler"/>
         * &lt;/sequence>
         * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="Handler" type="{}_Handler"/>
         * &lt;/sequence>
         * &lt;attribute name="base-diagram" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="stereotype" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("793716ce-65cf-44a3-b29d-64678d35d6e2")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "diagramType"
        })
        public static class Jxbv2Diagrams {
            @objid ("3191b9f0-4a89-4338-9468-9c265a3ec4e7")
            @XmlElement (name = "DiagramType")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType> diagramType;

            /**
             * Gets the value of the diagramType property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
             * diagramType property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getDiagramType().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Module.Gui.Diagrams.DiagramType }
             */
            @objid ("01685687-b88f-4115-aee5-4aa76c302cb4")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType> getDiagramType() {
                if (this.diagramType == null) {
                    this.diagramType = new ArrayList<>();
                }
                return this.diagramType;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="Palette" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="ToolRef" maxOccurs="unbounded" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;extension base="{}_ToolRef">
             * &lt;/extension>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/sequence>
             * &lt;attribute name="keepBasePalette" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="Wizard" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
             * &lt;element name="Handler" type="{}_Handler"/>
             * &lt;/sequence>
             * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="Handler" type="{}_Handler"/>
             * &lt;/sequence>
             * &lt;attribute name="base-diagram" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="stereotype" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("7aece06d-8970-4093-b51c-bc23866b7e1b")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "", propOrder = {
                    "palette",
                    "wizard",
                    "handler"
            })
            public static class Jxbv2DiagramType {
                @objid ("1acbe39a-b954-4a39-80c1-8b909e5fc82b")
                @XmlAttribute (name = "base-diagram", required = true)
                protected String baseDiagram;

                @objid ("23119cf8-1456-4659-8009-426303a3a837")
                @XmlAttribute (name = "stereotype", required = true)
                protected String stereotype;

                @objid ("2fd56beb-a6aa-48ad-b71e-fc5eaffe130c")
                @XmlElement (name = "Palette")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette palette;

                @objid ("8839c39d-8619-4d01-9305-760e30b8cdc6")
                @XmlElement (name = "Wizard")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Wizard wizard;

                @objid ("691eea4a-2e72-45de-b97d-431eb5287424")
                @XmlElement (name = "Handler", required = true)
                protected Jxbv2Handler handler;

                /**
                 * Gets the value of the palette property.
                 * @return possible object is {@link Module.Gui.Diagrams.DiagramType.Palette }
                 */
                @objid ("f0ec1f1d-e6d3-4519-9f9a-222ac637da36")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette getPalette() {
                    return this.palette;
                }

                /**
                 * Sets the value of the palette property.
                 * @param value allowed object is {@link Module.Gui.Diagrams.DiagramType.Palette }
                 */
                @objid ("7326a833-35f4-4e07-8741-ef8f47bc6f4d")
                public void setPalette(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette value) {
                    this.palette = value;
                }

                /**
                 * Gets the value of the wizard property.
                 * @return possible object is {@link Module.Gui.Diagrams.DiagramType.Wizard }
                 */
                @objid ("ad6b1d5e-da42-47ce-83ec-af2e9b774ca3")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Wizard getWizard() {
                    return this.wizard;
                }

                /**
                 * Sets the value of the wizard property.
                 * @param value allowed object is {@link Module.Gui.Diagrams.DiagramType.Wizard }
                 */
                @objid ("b5296efb-2c40-4ab6-8812-5a01e7aa30b8")
                public void setWizard(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Wizard value) {
                    this.wizard = value;
                }

                /**
                 * Gets the value of the baseDiagram property.
                 * @return possible object is {@link String }
                 */
                @objid ("dad6be81-e7de-4866-83f9-26bd7e4576c6")
                public String getBaseDiagram() {
                    return this.baseDiagram;
                }

                /**
                 * Sets the value of the baseDiagram property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("abf85f01-2f22-48a7-9503-9b2273ad6a5e")
                public void setBaseDiagram(String value) {
                    this.baseDiagram = value;
                }

                /**
                 * Gets the value of the stereotype property.
                 * @return possible object is {@link String }
                 */
                @objid ("bae60d68-cab9-407d-88f7-22456815d839")
                public String getStereotype() {
                    return this.stereotype;
                }

                /**
                 * Sets the value of the stereotype property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("d379fd65-2d47-4fde-af94-fa9c1d813351")
                public void setStereotype(String value) {
                    this.stereotype = value;
                }

                /**
                 * Gets the value of the handler property.
                 * @return possible object is {@link Jxbv2Handler }
                 */
                @objid ("64c592b2-b630-4866-b73b-d4ce2dedd53c")
                public Jxbv2Handler getHandler() {
                    return this.handler;
                }

                /**
                 * Sets the value of the handler property.
                 * @param value allowed object is {@link Jxbv2Handler }
                 */
                @objid ("53f07350-e0b7-432b-b435-d63a25e03707")
                public void setHandler(Jxbv2Handler value) {
                    this.handler = value;
                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="ToolRef" maxOccurs="unbounded" minOccurs="0">
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;extension base="{}_ToolRef">
                 * &lt;/extension>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * &lt;/element>
                 * &lt;/sequence>
                 * &lt;attribute name="keepBasePalette" type="{http://www.w3.org/2001/XMLSchema}boolean" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("f75a6d3f-bd9e-4300-8bc8-a823baf34de0")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "toolRef"
                })
                public static class Jxbv2Palette {
                    @objid ("2a75698f-ca0b-4d2a-a0e0-f1847064adf4")
                    @XmlAttribute (name = "keepBasePalette")
                    protected Boolean keepBasePalette;

                    @objid ("bb080627-4f6c-4b91-a644-ad5bef9d7d2e")
                    @XmlElement (name = "ToolRef")
                    protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette.Jxbv2ToolRef> toolRef;

                    /**
                     * Gets the value of the toolRef property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * toolRef property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getToolRef().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link Module.Gui.Diagrams.DiagramType.Palette.ToolRef }
                     */
                    @objid ("7d0b32fa-7cfa-47d1-bbf9-cf9a4577b494")
                    public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette.Jxbv2ToolRef> getToolRef() {
                        if (this.toolRef == null) {
                            this.toolRef = new ArrayList<>();
                        }
                        return this.toolRef;
                    }

                    /**
                     * Gets the value of the keepBasePalette property.
                     * @return possible object is {@link Boolean }
                     */
                    @objid ("279ec773-081f-40f3-bccd-27cde18b7a48")
                    public Boolean isKeepBasePalette() {
                        return this.keepBasePalette;
                    }

                    /**
                     * Sets the value of the keepBasePalette property.
                     * @param value allowed object is {@link Boolean }
                     */
                    @objid ("c3e22f01-c160-4f62-9ce6-374c599df712")
                    public void setKeepBasePalette(Boolean value) {
                        this.keepBasePalette = value;
                    }

                    /**
                     * <p>
                     * Java class for anonymous complex type.
                     * 
                     * <p>
                     * The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType>
                     * &lt;complexContent>
                     * &lt;extension base="{}_ToolRef">
                     * &lt;/extension>
                     * &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     */
                    @objid ("cc33ef13-a1b3-4cc1-9bda-0597a9a928a9")
                    @XmlAccessorType (XmlAccessType.FIELD)
                    @XmlType (name = "")
                    public static class Jxbv2ToolRef extends org.modelio.gproject.data.module.jaxbv2.Jxbv2ToolRef {
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
                 * &lt;element name="Handler" type="{}_Handler"/>
                 * &lt;/sequence>
                 * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("222a8258-c805-4f3e-8c73-203a388df3b1")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "scope",
                        "handler"
                })
                public static class Jxbv2Wizard {
                    @objid ("69eb5258-97cd-4f10-b3d9-002cdeb11c94")
                    @XmlAttribute (name = "label", required = true)
                    protected String label;

                    @objid ("8f54f047-14c2-48d7-a4ef-10918a7606ba")
                    @XmlAttribute (name = "icon")
                    protected String icon;

                    @objid ("a0792943-a663-4102-b96d-032681ec4083")
                    @XmlAttribute (name = "information")
                    protected String information;

                    @objid ("2d4f6be6-56f5-4e94-a952-eed8a8fdf176")
                    @XmlAttribute (name = "details")
                    protected String details;

                    @objid ("3127d23f-d29e-454f-a4ab-95b936efe005")
                    @XmlAttribute (name = "help-url")
                    protected String helpUrl;

                    @objid ("51fe9af3-170e-44c8-b640-01212fa245b1")
                    @XmlAttribute (name = "preview-image")
                    protected String previewImage;

                    @objid ("cbf281aa-119e-4a74-85cd-9f6cf4251e8c")
                    @XmlElement (name = "Scope", required = true)
                    protected List<Jxbv2Scope> scope;

                    @objid ("d59ae6e1-528f-410d-b72e-09221ff9996f")
                    @XmlElement (name = "Handler", required = true)
                    protected Jxbv2Handler handler;

                    /**
                     * Gets the value of the scope property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * scope property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getScope().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link Jxbv2Scope }
                     */
                    @objid ("6e6eb433-8834-4021-970e-f3a9cfeec7fe")
                    public List<Jxbv2Scope> getScope() {
                        if (this.scope == null) {
                            this.scope = new ArrayList<>();
                        }
                        return this.scope;
                    }

                    /**
                     * Gets the value of the handler property.
                     * @return possible object is {@link Jxbv2Handler }
                     */
                    @objid ("49846d01-5786-4955-a1af-1bfb903d06cc")
                    public Jxbv2Handler getHandler() {
                        return this.handler;
                    }

                    /**
                     * Sets the value of the handler property.
                     * @param value allowed object is {@link Jxbv2Handler }
                     */
                    @objid ("c8017c82-4d1c-4273-b1ad-a4a61f77d3e7")
                    public void setHandler(Jxbv2Handler value) {
                        this.handler = value;
                    }

                    /**
                     * Gets the value of the label property.
                     * @return possible object is {@link String }
                     */
                    @objid ("293fd124-bfd0-4a49-9151-6fe4da7dd21f")
                    public String getLabel() {
                        return this.label;
                    }

                    /**
                     * Sets the value of the label property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("dcf8ef98-9b8e-4077-81e5-321a2d822303")
                    public void setLabel(String value) {
                        this.label = value;
                    }

                    /**
                     * Gets the value of the icon property.
                     * @return possible object is {@link String }
                     */
                    @objid ("09e97f36-bd1b-495f-86c5-542a9ef15cf0")
                    public String getIcon() {
                        return this.icon;
                    }

                    /**
                     * Sets the value of the icon property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("b7db95b4-6b48-442e-a5f4-f2a073cb82a0")
                    public void setIcon(String value) {
                        this.icon = value;
                    }

                    /**
                     * Gets the value of the information property.
                     * @return possible object is {@link String }
                     */
                    @objid ("b521ab7f-180c-495d-8f46-c6b198912c77")
                    public String getInformation() {
                        return this.information;
                    }

                    /**
                     * Sets the value of the information property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("1cf2f470-cb56-4233-ae97-a7d77589cb8b")
                    public void setInformation(String value) {
                        this.information = value;
                    }

                    /**
                     * Gets the value of the details property.
                     * @return possible object is {@link String }
                     */
                    @objid ("17d1b589-714d-40a4-be05-e6e176ca96c3")
                    public String getDetails() {
                        return this.details;
                    }

                    /**
                     * Sets the value of the details property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("c1e7ab3d-54b6-4044-9d74-f93a8b2f6c33")
                    public void setDetails(String value) {
                        this.details = value;
                    }

                    /**
                     * Gets the value of the helpUrl property.
                     * @return possible object is {@link String }
                     */
                    @objid ("6fa7a5ce-de12-42e7-8c33-c913870581c3")
                    public String getHelpUrl() {
                        return this.helpUrl;
                    }

                    /**
                     * Sets the value of the helpUrl property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("25038634-9edb-4d82-93bc-8a3c7c898b2a")
                    public void setHelpUrl(String value) {
                        this.helpUrl = value;
                    }

                    /**
                     * Gets the value of the previewImage property.
                     * @return possible object is {@link String }
                     */
                    @objid ("9f0bad58-f86c-4a8d-b139-a2a1f3383599")
                    public String getPreviewImage() {
                        return this.previewImage;
                    }

                    /**
                     * Sets the value of the previewImage property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("70777fb2-4b9f-403a-a040-bfccbd4cb68d")
                    public void setPreviewImage(String value) {
                        this.previewImage = value;
                    }

                }

            }

        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Tool" type="{}_Tool" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("7692772d-a3fd-4a20-aac1-5e6992a5f63f")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "tool"
        })
        public static class Jxbv2Tools {
            @objid ("01a7cebe-4298-4306-8152-f37a2c73393d")
            @XmlElement (name = "Tool")
            protected List<Jxbv2Tool> tool;

            /**
             * Gets the value of the tool property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the tool
             * property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getTool().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Tool }
             */
            @objid ("731edb2c-7941-4416-ab1d-7d7553c8111c")
            public List<Jxbv2Tool> getTool() {
                if (this.tool == null) {
                    this.tool = new ArrayList<>();
                }
                return this.tool;
            }

        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="PropertyPage" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="CommandRef" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;extension base="{}_CommandRef">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("d714d90d-117e-40d2-9100-f2cd86453141")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "propertyPage"
        })
        public static class Jxbv2Views {
            @objid ("9ca7c73a-7452-42c5-9505-372c58568545")
            @XmlElement (name = "PropertyPage")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage> propertyPage;

            /**
             * Gets the value of the propertyPage property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
             * propertyPage property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getPropertyPage().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Module.Gui.Views.PropertyPage }
             */
            @objid ("14912217-bcfc-407b-a474-ebc198a635a1")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage> getPropertyPage() {
                if (this.propertyPage == null) {
                    this.propertyPage = new ArrayList<>();
                }
                return this.propertyPage;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="CommandRef" maxOccurs="unbounded" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;extension base="{}_CommandRef">
             * &lt;/extension>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/sequence>
             * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="image" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("5be877e2-8475-4810-8624-98ac1a219788")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "", propOrder = {
                    "commandRef"
            })
            public static class Jxbv2PropertyPage {
                @objid ("a66f74a3-2fcb-45c0-a31d-8dedfe53929c")
                @XmlAttribute (name = "id", required = true)
                protected String id;

                @objid ("a579c607-e239-4e7b-b142-8b6b41369154")
                @XmlAttribute (name = "class", required = true)
                protected String clazz;

                @objid ("40abf254-041e-4628-b58d-9de72ccb9703")
                @XmlAttribute (name = "label")
                protected String label;

                @objid ("b98b4542-4a79-42a3-b04b-8373ed201ea8")
                @XmlAttribute (name = "image")
                protected String image;

                @objid ("b512bcc8-f729-4492-980e-6c4a57fba917")
                @XmlElement (name = "CommandRef")
                protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage.Jxbv2CommandRef> commandRef;

                /**
                 * Gets the value of the commandRef property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                 * commandRef property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * 
                 * <pre>
                 * getCommandRef().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list {@link Module.Gui.Views.PropertyPage.CommandRef }
                 */
                @objid ("b6392534-03b0-47d4-a78c-1fd47f4b4a51")
                public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Views.Jxbv2PropertyPage.Jxbv2CommandRef> getCommandRef() {
                    if (this.commandRef == null) {
                        this.commandRef = new ArrayList<>();
                    }
                    return this.commandRef;
                }

                /**
                 * Gets the value of the id property.
                 * @return possible object is {@link String }
                 */
                @objid ("00ee2280-c3be-4b0b-9c7d-bbdbdea26d5b")
                public String getId() {
                    return this.id;
                }

                /**
                 * Sets the value of the id property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("31fa88d3-ccb6-4b18-b41b-d4373dc5988d")
                public void setId(String value) {
                    this.id = value;
                }

                /**
                 * Gets the value of the clazz property.
                 * @return possible object is {@link String }
                 */
                @objid ("117d6a42-a166-4099-9f94-775f845785b6")
                public String getClazz() {
                    return this.clazz;
                }

                /**
                 * Sets the value of the clazz property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("787f70d9-c430-4624-98bb-80296134f6c8")
                public void setClazz(String value) {
                    this.clazz = value;
                }

                /**
                 * Gets the value of the label property.
                 * @return possible object is {@link String }
                 */
                @objid ("9663903c-b8a9-427c-a067-51bec937877f")
                public String getLabel() {
                    return this.label;
                }

                /**
                 * Sets the value of the label property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("cf18d6ad-7647-4be7-929e-bbbb3ddf66fc")
                public void setLabel(String value) {
                    this.label = value;
                }

                /**
                 * Gets the value of the image property.
                 * @return possible object is {@link String }
                 */
                @objid ("3239c277-8767-42e1-9525-d0e905e65fbf")
                public String getImage() {
                    return this.image;
                }

                /**
                 * Sets the value of the image property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("fb37c398-bcaa-4175-867a-ec69e96326f7")
                public void setImage(String value) {
                    this.image = value;
                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;extension base="{}_CommandRef">
                 * &lt;/extension>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("8d7c0a1c-b784-48bd-aef1-28fdf8debd3a")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "")
                public static class Jxbv2CommandRef extends org.modelio.gproject.data.module.jaxbv2.Jxbv2CommandRef {
                }

            }

        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="MatrixType" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="X">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;extension base="{}_Handler">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="Y" type="{}_Handler"/>
         * &lt;element name="Z" type="{}_Handler" minOccurs="0"/>
         * &lt;element name="Val" type="{}_Handler"/>
         * &lt;element name="Wizard" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
         * &lt;element name="Handler" type="{}_Handler"/>
         * &lt;/sequence>
         * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="stereotype" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("9e650207-1f69-4c17-9792-d72751cb2909")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "matrixType"
        })
        public static class Jxbv2Matrices {
            @objid ("469f1673-1fbb-4518-9a47-f1906160e9c9")
            @XmlElement (name = "MatrixType")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType> matrixType;

            /**
             * Gets the value of the matrixType property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the matrixType
             * property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getMatrixType().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Module.Gui.Matrices.MatrixType }
             */
            @objid ("0eeade91-e63e-41c7-9d1e-75325b28a931")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType> getMatrixType() {
                if (this.matrixType == null) {
                    this.matrixType = new ArrayList<>();
                }
                return this.matrixType;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="X">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;extension base="{}_Handler">
             * &lt;/extension>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="Y" type="{}_Handler"/>
             * &lt;element name="Z" type="{}_Handler" minOccurs="0"/>
             * &lt;element name="Val" type="{}_Handler"/>
             * &lt;element name="Wizard" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
             * &lt;element name="Handler" type="{}_Handler"/>
             * &lt;/sequence>
             * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/sequence>
             * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="stereotype" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("fa82a3a9-03a9-497a-a02c-24f2ab4f5267")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "", propOrder = {
                    "x",
                    "y",
                    "z",
                    "val",
                    "wizard"
            })
            public static class Jxbv2MatrixType {
                @objid ("5b47b5d4-f735-4f03-a68f-7ceea1da7ce3")
                @XmlAttribute (name = "id", required = true)
                protected String id;

                @objid ("917a805c-2d73-4cf9-9679-681d48baf182")
                @XmlAttribute (name = "stereotype", required = true)
                protected String stereotype;

                @objid ("5e8bf5fc-4811-40cc-ad1e-2f83028b6573")
                @XmlElement (name = "X", required = true)
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2X x;

                @objid ("492a8a83-db8b-454e-8e7b-fd7978944c0d")
                @XmlElement (name = "Y", required = true)
                protected Jxbv2Handler y;

                @objid ("7bda7fa7-d77b-476b-b99c-b6d3a2998b00")
                @XmlElement (name = "Z")
                protected Jxbv2Handler z;

                @objid ("0b4f3b22-c0f5-4981-ac18-a6c2229beb4c")
                @XmlElement (name = "Val", required = true)
                protected Jxbv2Handler val;

                @objid ("84b55313-9c01-4904-9cf2-7d1bd5e909b8")
                @XmlElement (name = "Wizard")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2Wizard wizard;

                /**
                 * Gets the value of the x property.
                 * @return possible object is {@link Module.Gui.Matrices.MatrixType.X }
                 */
                @objid ("c5de3288-f04b-430a-bcda-430fa1151734")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2X getX() {
                    return this.x;
                }

                /**
                 * Sets the value of the x property.
                 * @param value allowed object is {@link Module.Gui.Matrices.MatrixType.X }
                 */
                @objid ("0f81c014-bee0-4a89-b3a4-c26af8461063")
                public void setX(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2X value) {
                    this.x = value;
                }

                /**
                 * Gets the value of the y property.
                 * @return possible object is {@link Jxbv2Handler }
                 */
                @objid ("6559dda3-7e15-4734-afa2-d5367d78bb1c")
                public Jxbv2Handler getY() {
                    return this.y;
                }

                /**
                 * Sets the value of the y property.
                 * @param value allowed object is {@link Jxbv2Handler }
                 */
                @objid ("7cc11eb1-b5d5-4ffe-a534-a1f309a653b3")
                public void setY(Jxbv2Handler value) {
                    this.y = value;
                }

                /**
                 * Gets the value of the z property.
                 * @return possible object is {@link Jxbv2Handler }
                 */
                @objid ("6f5fab9a-4efb-44a8-9267-ac078d310f0d")
                public Jxbv2Handler getZ() {
                    return this.z;
                }

                /**
                 * Sets the value of the z property.
                 * @param value allowed object is {@link Jxbv2Handler }
                 */
                @objid ("03fd28e2-95d0-4bec-9a68-639148f51468")
                public void setZ(Jxbv2Handler value) {
                    this.z = value;
                }

                /**
                 * Gets the value of the val property.
                 * @return possible object is {@link Jxbv2Handler }
                 */
                @objid ("9132c1a6-d4d5-4660-b8f3-4cd06ab96340")
                public Jxbv2Handler getVal() {
                    return this.val;
                }

                /**
                 * Sets the value of the val property.
                 * @param value allowed object is {@link Jxbv2Handler }
                 */
                @objid ("7d2c8465-76d4-42f8-855c-58f48687856c")
                public void setVal(Jxbv2Handler value) {
                    this.val = value;
                }

                /**
                 * Gets the value of the wizard property.
                 * @return possible object is {@link Module.Gui.Matrices.MatrixType.Wizard }
                 */
                @objid ("ed8c8c45-a373-40fc-b027-742e5ad1f11e")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2Wizard getWizard() {
                    return this.wizard;
                }

                /**
                 * Sets the value of the wizard property.
                 * @param value allowed object is {@link Module.Gui.Matrices.MatrixType.Wizard }
                 */
                @objid ("ab946704-97cc-462d-abeb-3b72ef98aa18")
                public void setWizard(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Matrices.Jxbv2MatrixType.Jxbv2Wizard value) {
                    this.wizard = value;
                }

                /**
                 * Gets the value of the id property.
                 * @return possible object is {@link String }
                 */
                @objid ("aff8ef89-138a-449c-9cd1-811d5c4406c2")
                public String getId() {
                    return this.id;
                }

                /**
                 * Sets the value of the id property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("48ee3150-e8ca-46f8-aca0-53c0c014e49f")
                public void setId(String value) {
                    this.id = value;
                }

                /**
                 * Gets the value of the stereotype property.
                 * @return possible object is {@link String }
                 */
                @objid ("14c21fa1-edfa-4e08-8fbb-e1ed345d882b")
                public String getStereotype() {
                    return this.stereotype;
                }

                /**
                 * Sets the value of the stereotype property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("21acaccb-7e5f-4f25-9aa4-2a91a3063803")
                public void setStereotype(String value) {
                    this.stereotype = value;
                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="Scope" type="{}_Scope" maxOccurs="unbounded"/>
                 * &lt;element name="Handler" type="{}_Handler"/>
                 * &lt;/sequence>
                 * &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="information" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="details" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="help-url" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;attribute name="preview-image" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("11372ae6-56a1-4e31-ab6f-12d60724482e")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "scope",
                        "handler"
                })
                public static class Jxbv2Wizard {
                    @objid ("259e9950-2aa4-4151-af5b-8609ee738505")
                    @XmlAttribute (name = "label", required = true)
                    protected String label;

                    @objid ("5aeac887-aad4-4c50-b97d-a8f0f29071f7")
                    @XmlAttribute (name = "icon")
                    protected String icon;

                    @objid ("974ff942-f5fb-4d34-9a8e-216bafb31d0b")
                    @XmlAttribute (name = "information")
                    protected String information;

                    @objid ("01a1604f-3462-4683-83c2-eb92f49c4b9c")
                    @XmlAttribute (name = "details")
                    protected String details;

                    @objid ("91e2b9cf-1a25-4cc3-9227-9633ae8eca7e")
                    @XmlAttribute (name = "help-url")
                    protected String helpUrl;

                    @objid ("0b9122cc-84db-43d6-b434-79bb203e8c9a")
                    @XmlAttribute (name = "preview-image")
                    protected String previewImage;

                    @objid ("994bcefd-475d-4873-8f0a-d4e77e1e3237")
                    @XmlElement (name = "Scope", required = true)
                    protected List<Jxbv2Scope> scope;

                    @objid ("cb1e804c-98d5-436a-8166-d038b714b65e")
                    @XmlElement (name = "Handler", required = true)
                    protected Jxbv2Handler handler;

                    /**
                     * Gets the value of the scope property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * scope property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getScope().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link Jxbv2Scope }
                     */
                    @objid ("032a2df2-4294-4d15-ac44-b4379e17de24")
                    public List<Jxbv2Scope> getScope() {
                        if (this.scope == null) {
                            this.scope = new ArrayList<>();
                        }
                        return this.scope;
                    }

                    /**
                     * Gets the value of the handler property.
                     * @return possible object is {@link Jxbv2Handler }
                     */
                    @objid ("303396ec-8d19-49ba-8a51-c084880e1027")
                    public Jxbv2Handler getHandler() {
                        return this.handler;
                    }

                    /**
                     * Sets the value of the handler property.
                     * @param value allowed object is {@link Jxbv2Handler }
                     */
                    @objid ("a16816a2-3c85-4efb-a4c8-254f0cfc8454")
                    public void setHandler(Jxbv2Handler value) {
                        this.handler = value;
                    }

                    /**
                     * Gets the value of the label property.
                     * @return possible object is {@link String }
                     */
                    @objid ("0a7d931c-3387-4f35-8625-40ce3f4d52a3")
                    public String getLabel() {
                        return this.label;
                    }

                    /**
                     * Sets the value of the label property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("ffa39b1f-ca09-4b04-b52c-c446b511c97f")
                    public void setLabel(String value) {
                        this.label = value;
                    }

                    /**
                     * Gets the value of the icon property.
                     * @return possible object is {@link String }
                     */
                    @objid ("7b143eba-9c40-4620-9c21-d7ba77c8fbb9")
                    public String getIcon() {
                        return this.icon;
                    }

                    /**
                     * Sets the value of the icon property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("b30ca794-7cc6-4561-bcba-d6a6de078879")
                    public void setIcon(String value) {
                        this.icon = value;
                    }

                    /**
                     * Gets the value of the information property.
                     * @return possible object is {@link String }
                     */
                    @objid ("5f8d4fdf-6ef3-44a2-b3d6-c2b0dd41d6de")
                    public String getInformation() {
                        return this.information;
                    }

                    /**
                     * Sets the value of the information property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("b76ca53d-9ea1-4991-851a-c0c63b53c299")
                    public void setInformation(String value) {
                        this.information = value;
                    }

                    /**
                     * Gets the value of the details property.
                     * @return possible object is {@link String }
                     */
                    @objid ("0cc43c60-4320-456b-bc88-3113d96b099b")
                    public String getDetails() {
                        return this.details;
                    }

                    /**
                     * Sets the value of the details property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("cf390b8b-2b0e-4f95-8fb6-7f242c97bb56")
                    public void setDetails(String value) {
                        this.details = value;
                    }

                    /**
                     * Gets the value of the helpUrl property.
                     * @return possible object is {@link String }
                     */
                    @objid ("3adbd021-9c22-42dc-a900-63ad94f4dee3")
                    public String getHelpUrl() {
                        return this.helpUrl;
                    }

                    /**
                     * Sets the value of the helpUrl property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("c1545827-8743-4f35-ba5d-54582a804624")
                    public void setHelpUrl(String value) {
                        this.helpUrl = value;
                    }

                    /**
                     * Gets the value of the previewImage property.
                     * @return possible object is {@link String }
                     */
                    @objid ("3ce200d2-895b-440e-9c79-13a046d6dbc3")
                    public String getPreviewImage() {
                        return this.previewImage;
                    }

                    /**
                     * Sets the value of the previewImage property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("3b7535dd-21e4-4c84-a262-91b27cd7e1d2")
                    public void setPreviewImage(String value) {
                        this.previewImage = value;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;extension base="{}_Handler">
                 * &lt;/extension>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("a21ee316-c8d2-43e0-9448-0ca292caab6f")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "")
                public static class Jxbv2X extends Jxbv2Handler {
                }

            }

        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Parameter" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence minOccurs="0">
     * &lt;element name="Enumeration" type="{}_Enumeration" minOccurs="0"/>
     * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="group" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="type">
     * &lt;simpleType>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     * &lt;enumeration value="Boolean"/>
     * &lt;enumeration value="Color"/>
     * &lt;enumeration value="Directory"/>
     * &lt;enumeration value="Enum"/>
     * &lt;enumeration value="File"/>
     * &lt;enumeration value="Integer"/>
     * &lt;enumeration value="Password"/>
     * &lt;enumeration value="String"/>
     * &lt;/restriction>
     * &lt;/simpleType>
     * &lt;/attribute>
     * &lt;attribute name="default-value" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("5e91941d-f3bd-48ac-9a0e-0238ee90b7dd")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "", propOrder = {
            "parameter"
    })
    public static class Jxbv2Parameters {
        @objid ("43ddedbd-5d06-4565-889a-7e6eae68da7d")
        @XmlElement (name = "Parameter")
        protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters.Jxbv2Parameter> parameter;

        /**
         * Gets the value of the parameter property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the parameter
         * property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getParameter().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Module.Parameters.Parameter }
         */
        @objid ("97218480-3dfe-4a69-bcc5-c8b37718e535")
        public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Parameters.Jxbv2Parameter> getParameter() {
            if (this.parameter == null) {
                this.parameter = new ArrayList<>();
            }
            return this.parameter;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence minOccurs="0">
         * &lt;element name="Enumeration" type="{}_Enumeration" minOccurs="0"/>
         * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="group" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="type">
         * &lt;simpleType>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         * &lt;enumeration value="Boolean"/>
         * &lt;enumeration value="Color"/>
         * &lt;enumeration value="Directory"/>
         * &lt;enumeration value="Enum"/>
         * &lt;enumeration value="File"/>
         * &lt;enumeration value="Integer"/>
         * &lt;enumeration value="Password"/>
         * &lt;enumeration value="String"/>
         * &lt;/restriction>
         * &lt;/simpleType>
         * &lt;/attribute>
         * &lt;attribute name="default-value" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("cc4761e9-cebf-44e9-82de-648c71fd467e")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "enumeration",
                "description"
        })
        public static class Jxbv2Parameter {
            @objid ("767c7c3d-b395-4758-9e33-e4e01acff78b")
            @XmlElement (name = "Description")
            protected String description;

            @objid ("60639896-491b-43a3-8fea-ec64fe970041")
            @XmlAttribute (name = "id", required = true)
            protected String id;

            @objid ("c50900b2-04ed-4421-a004-a066f5a9ca40")
            @XmlAttribute (name = "group", required = true)
            protected String group;

            @objid ("54ff27e3-1ac7-4a5c-94d7-57b6904320ca")
            @XmlAttribute (name = "uid")
            protected String uid;

            @objid ("86076be0-83e7-46b4-84a1-307edd0e51a1")
            @XmlAttribute (name = "type")
            protected String type;

            @objid ("414647af-8a47-4162-a780-a0fdb91c4f8e")
            @XmlAttribute (name = "default-value")
            protected String defaultValue;

            @objid ("9cf1d567-098c-468b-add0-5215adfec729")
            @XmlAttribute (name = "label")
            protected String label;

            @objid ("062b3722-9f7c-4bb9-9bc2-d950351036c5")
            @XmlElement (name = "Enumeration")
            protected Jxbv2Enumeration enumeration;

            /**
             * Gets the value of the enumeration property.
             * @return possible object is {@link Enumeration }
             */
            @objid ("d4dd4bc3-db4b-49fe-a71f-9e3c0a2b1b62")
            public Jxbv2Enumeration getEnumeration() {
                return this.enumeration;
            }

            /**
             * Sets the value of the enumeration property.
             * @param value allowed object is {@link Enumeration }
             */
            @objid ("fa4a0e64-4033-4081-91e9-c175b8231a3c")
            public void setEnumeration(Jxbv2Enumeration value) {
                this.enumeration = value;
            }

            /**
             * Gets the value of the description property.
             * @return possible object is {@link String }
             */
            @objid ("706f2be9-6599-4b51-895f-29390225d21b")
            public String getDescription() {
                return this.description;
            }

            /**
             * Sets the value of the description property.
             * @param value allowed object is {@link String }
             */
            @objid ("2da7ad75-3271-40eb-945d-fdb8434b466c")
            public void setDescription(String value) {
                this.description = value;
            }

            /**
             * Gets the value of the id property.
             * @return possible object is {@link String }
             */
            @objid ("ba678cd3-b87e-4432-9150-487d0a7ea3e1")
            public String getId() {
                return this.id;
            }

            /**
             * Sets the value of the id property.
             * @param value allowed object is {@link String }
             */
            @objid ("ac911dcc-a103-46d0-9d9a-21b9258472fd")
            public void setId(String value) {
                this.id = value;
            }

            /**
             * Gets the value of the group property.
             * @return possible object is {@link String }
             */
            @objid ("7c5a24ad-85fd-46b9-bd48-0b4643e92d94")
            public String getGroup() {
                return this.group;
            }

            /**
             * Sets the value of the group property.
             * @param value allowed object is {@link String }
             */
            @objid ("c3bc1931-dfa0-4b48-8665-6007e7b9328e")
            public void setGroup(String value) {
                this.group = value;
            }

            /**
             * Gets the value of the uid property.
             * @return possible object is {@link String }
             */
            @objid ("26f9fd1a-d410-4c42-b78b-fa7183aef8df")
            public String getUid() {
                return this.uid;
            }

            /**
             * Sets the value of the uid property.
             * @param value allowed object is {@link String }
             */
            @objid ("db1fbf92-cf1a-4f65-a11d-0ba806564442")
            public void setUid(String value) {
                this.uid = value;
            }

            /**
             * Gets the value of the type property.
             * @return possible object is {@link String }
             */
            @objid ("1d71d976-c1d1-4f82-912f-e39663c479c2")
            public String getType() {
                return this.type;
            }

            /**
             * Sets the value of the type property.
             * @param value allowed object is {@link String }
             */
            @objid ("08d20cd6-468b-43c7-b149-80f98c3fcce8")
            public void setType(String value) {
                this.type = value;
            }

            /**
             * Gets the value of the defaultValue property.
             * @return possible object is {@link String }
             */
            @objid ("a7408944-1559-4bff-9d81-c4f571c60561")
            public String getDefaultValue() {
                return this.defaultValue;
            }

            /**
             * Sets the value of the defaultValue property.
             * @param value allowed object is {@link String }
             */
            @objid ("2f3f2e93-8293-42cc-99b6-4489e868b871")
            public void setDefaultValue(String value) {
                this.defaultValue = value;
            }

            /**
             * Gets the value of the label property.
             * @return possible object is {@link String }
             */
            @objid ("aace14c7-c769-4b06-850a-10d21b33a247")
            public String getLabel() {
                return this.label;
            }

            /**
             * Sets the value of the label property.
             * @param value allowed object is {@link String }
             */
            @objid ("18b14550-918a-454c-b645-e1e86293505a")
            public void setLabel(String value) {
                this.label = value;
            }

        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Profile" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Stereotype" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     * &lt;element name="Icon" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Image" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;choice minOccurs="0">
     * &lt;element name="PropertyTable" type="{}_PropertyTableDefinition" minOccurs="0"/>
     * &lt;element name="TagTypes" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;element name="NoteTypes" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="ExternDocumentTypes" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Constraints" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Constraint" type="{}_LinkConstraint" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Extensions" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="metaclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="owner-stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="is-abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="MetaclassReference" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;choice minOccurs="0">
     * &lt;element name="PropertyTable" type="{}_PropertyTableDefinition" minOccurs="0"/>
     * &lt;element name="TagTypes" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/choice>
     * &lt;element name="NoteTypes" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="ExternDocumentTypes" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="metaclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Extensions" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("de63257d-c3a5-45d6-953c-538a33bbbfe7")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "", propOrder = {
            "profile"
    })
    public static class Jxbv2Profiles {
        @objid ("eda4109e-1f48-42a1-a8fd-d7320fc6c198")
        @XmlElement (name = "Profile")
        protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile> profile;

        /**
         * Gets the value of the profile property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the profile
         * property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getProfile().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Module.Profiles.Profile }
         */
        @objid ("705ad83f-e91d-4df4-95fc-5ee133a0d8a2")
        public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile> getProfile() {
            if (this.profile == null) {
                this.profile = new ArrayList<>();
            }
            return this.profile;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Stereotype" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         * &lt;element name="Icon" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="Image" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;choice minOccurs="0">
         * &lt;element name="PropertyTable" type="{}_PropertyTableDefinition" minOccurs="0"/>
         * &lt;element name="TagTypes" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/choice>
         * &lt;element name="NoteTypes" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="ExternDocumentTypes" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="Constraints" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Constraint" type="{}_LinkConstraint" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="Extensions" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="metaclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="owner-stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="is-abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="MetaclassReference" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;choice minOccurs="0">
         * &lt;element name="PropertyTable" type="{}_PropertyTableDefinition" minOccurs="0"/>
         * &lt;element name="TagTypes" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/choice>
         * &lt;element name="NoteTypes" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="ExternDocumentTypes" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="metaclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;element name="Extensions" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("c169d7d6-ab5a-4896-96d7-fa3c376b0f86")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "stereotype",
                "metaclassReference",
                "extensions"
        })
        public static class Jxbv2Profile {
            @objid ("307c6006-c7a6-42fc-b4d7-03d121ccdd6d")
            @XmlAttribute (name = "uid")
            protected String uid;

            @objid ("13e771e4-fab5-4330-a088-c7389a90b767")
            @XmlAttribute (name = "id", required = true)
            protected String id;

            @objid ("8000d1ec-8cbc-4517-8f49-c1f1196bb870")
            @XmlElement (name = "Stereotype")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype> stereotype;

            @objid ("7ac55428-d0c7-4b42-b634-8259ff789ca4")
            @XmlElement (name = "MetaclassReference")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference> metaclassReference;

            @objid ("fe92fdb8-db86-4033-a603-b901874f6069")
            @XmlElement (name = "Extensions")
            protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Extensions extensions;

            /**
             * Gets the value of the stereotype property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the stereotype
             * property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getStereotype().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype }
             */
            @objid ("7e3616bf-88b6-4e48-be9f-4aca43dc8b9a")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype> getStereotype() {
                if (this.stereotype == null) {
                    this.stereotype = new ArrayList<>();
                }
                return this.stereotype;
            }

            /**
             * Gets the value of the metaclassReference property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
             * metaclassReference property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getMetaclassReference().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference }
             */
            @objid ("e75ddf93-196b-432d-bb12-d20e2beae79c")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference> getMetaclassReference() {
                if (this.metaclassReference == null) {
                    this.metaclassReference = new ArrayList<>();
                }
                return this.metaclassReference;
            }

            /**
             * Gets the value of the uid property.
             * @return possible object is {@link String }
             */
            @objid ("cbe69ceb-0fce-4594-ae0a-59905f3ad991")
            public String getUid() {
                return this.uid;
            }

            /**
             * Sets the value of the uid property.
             * @param value allowed object is {@link String }
             */
            @objid ("ec2d88c1-7976-4657-a67a-489480c30800")
            public void setUid(String value) {
                this.uid = value;
            }

            /**
             * Gets the value of the id property.
             * @return possible object is {@link String }
             */
            @objid ("7f43adc8-4598-4238-8b62-2ef61bc9d639")
            public String getId() {
                return this.id;
            }

            /**
             * Sets the value of the id property.
             * @param value allowed object is {@link String }
             */
            @objid ("6393d7ea-1011-484d-99bb-d48b194be1d2")
            public void setId(String value) {
                this.id = value;
            }

            /**
             * Gets the value of the extensions property.
             * @return possible object is {@link Jxbv2NoteType.Jxbv2Extensions }
             */
            @objid ("863b9ea1-1b8b-41c2-b850-f520bfc63418")
            public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Extensions getExtensions() {
                return this.extensions;
            }

            /**
             * Sets the value of the extensions property.
             * @param value allowed object is {@link Jxbv2NoteType.Jxbv2Extensions }
             */
            @objid ("c07183da-00ca-4e2a-a845-374b7cd6d303")
            public void setExtensions(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Extensions value) {
                this.extensions = value;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;choice minOccurs="0">
             * &lt;element name="PropertyTable" type="{}_PropertyTableDefinition" minOccurs="0"/>
             * &lt;element name="TagTypes" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/choice>
             * &lt;element name="NoteTypes" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="ExternDocumentTypes" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/sequence>
             * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="metaclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("71db6e4a-b903-4536-b36b-46f0f8f66178")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "", propOrder = {
                    "propertyTable",
                    "tagTypes",
                    "noteTypes",
                    "externDocumentTypes"
            })
            public static class Jxbv2MetaclassReference {
                @objid ("e1078f37-2b38-433c-adab-ac3a61492439")
                @XmlAttribute (name = "uid")
                protected String uid;

                @objid ("9732be9f-4c6f-4531-821f-22f2977fefcb")
                @XmlAttribute (name = "metaclass", required = true)
                protected String metaclass;

                @objid ("9ef3e4fb-fe42-4be6-9fba-2cbe02e98981")
                @XmlElement (name = "TagTypes")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2TagTypes tagTypes;

                @objid ("9414f216-5085-4da2-ad3b-cdb6cdf2c45c")
                @XmlElement (name = "NoteTypes")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2NoteTypes noteTypes;

                @objid ("3b6787e6-ec6b-40aa-a2c4-17ca711f1edf")
                @XmlElement (name = "ExternDocumentTypes")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2ExternDocumentTypes externDocumentTypes;

                @objid ("cba313b1-8e85-4b1b-8b16-4f0f4436d65b")
                @XmlElement (name = "PropertyTable")
                protected Jxbv2PropertyTableDefinition propertyTable;

                /**
                 * Gets the value of the tagTypes property.
                 * @return possible object is {@link Module.Profiles.Profile.MetaclassReference.TagTypes }
                 */
                @objid ("c24c3b4a-79b0-4cbe-a322-b462833aa78c")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2TagTypes getTagTypes() {
                    return this.tagTypes;
                }

                /**
                 * Sets the value of the tagTypes property.
                 * @param value allowed object is {@link Module.Profiles.Profile.MetaclassReference.TagTypes }
                 */
                @objid ("c286a6ca-66d8-4463-bc72-1612cb183b53")
                public void setTagTypes(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2TagTypes value) {
                    this.tagTypes = value;
                }

                /**
                 * Gets the value of the noteTypes property.
                 * @return possible object is {@link Module.Profiles.Profile.MetaclassReference.NoteTypes }
                 */
                @objid ("e6b2c8a8-eef3-45ee-b185-bebb5caa72d7")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2NoteTypes getNoteTypes() {
                    return this.noteTypes;
                }

                /**
                 * Sets the value of the noteTypes property.
                 * @param value allowed object is {@link Module.Profiles.Profile.MetaclassReference.NoteTypes }
                 */
                @objid ("33d9464b-999f-4df6-9c20-6c43bacd2d8f")
                public void setNoteTypes(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2NoteTypes value) {
                    this.noteTypes = value;
                }

                /**
                 * Gets the value of the externDocumentTypes property.
                 * @return possible object is {@link Module.Profiles.Profile.MetaclassReference.ExternDocumentTypes }
                 */
                @objid ("5da4b77c-552a-4848-8eea-020ef920e604")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2ExternDocumentTypes getExternDocumentTypes() {
                    return this.externDocumentTypes;
                }

                /**
                 * Sets the value of the externDocumentTypes property.
                 * @param value allowed object is {@link Module.Profiles.Profile.MetaclassReference.ExternDocumentTypes }
                 */
                @objid ("dc3df89b-e4c6-466f-8fcb-aa9feb2de18b")
                public void setExternDocumentTypes(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2MetaclassReference.Jxbv2ExternDocumentTypes value) {
                    this.externDocumentTypes = value;
                }

                /**
                 * Gets the value of the uid property.
                 * @return possible object is {@link String }
                 */
                @objid ("3929595e-bddf-4d35-8523-c2a9f197650f")
                public String getUid() {
                    return this.uid;
                }

                /**
                 * Sets the value of the uid property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("e508ca67-558f-4c07-badb-f364df490378")
                public void setUid(String value) {
                    this.uid = value;
                }

                /**
                 * Gets the value of the metaclass property.
                 * @return possible object is {@link String }
                 */
                @objid ("0dcce346-ad3d-4d67-81d0-aca93bee5fbc")
                public String getMetaclass() {
                    return this.metaclass;
                }

                /**
                 * Sets the value of the metaclass property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("63c47334-7a27-44db-8cfc-13aca3230c8f")
                public void setMetaclass(String value) {
                    this.metaclass = value;
                }

                /**
                 * Gets the value of the propertyTable property.
                 * @return possible object is {@link Jxbv2PropertyTableDefinition }
                 */
                @objid ("929e32df-88d9-4ef9-a426-d6131500dd4c")
                public Jxbv2PropertyTableDefinition getPropertyTable() {
                    return this.propertyTable;
                }

                /**
                 * Sets the value of the propertyTable property.
                 * @param value allowed object is {@link Jxbv2PropertyTableDefinition }
                 */
                @objid ("1674a31b-e5f4-42a7-8e21-8c6778217bc7")
                public void setPropertyTable(Jxbv2PropertyTableDefinition value) {
                    this.propertyTable = value;
                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
                 * &lt;/sequence>
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("a6996830-180f-4855-932e-8a36efa7efff")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "externDocumentType"
                })
                public static class Jxbv2ExternDocumentTypes {
                    @objid ("20f96772-f807-40af-ab99-217623b1f185")
                    @XmlElement (name = "ExternDocumentType")
                    protected List<Jxbv2ExternDocumentType> externDocumentType;

                    /**
                     * Gets the value of the externDocumentType property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * externDocumentType property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getExternDocumentType().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link ExternDocumentType }
                     */
                    @objid ("e21268b7-c8e9-41dc-b25f-770a4ad0a37f")
                    public List<Jxbv2ExternDocumentType> getExternDocumentType() {
                        if (this.externDocumentType == null) {
                            this.externDocumentType = new ArrayList<>();
                        }
                        return this.externDocumentType;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
                 * &lt;/sequence>
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("5af2424f-f965-476a-a829-4c374bd6fe38")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "noteType"
                })
                public static class Jxbv2NoteTypes {
                    @objid ("9cd837b4-1987-40eb-bf0e-5d67a62cf40a")
                    @XmlElement (name = "NoteType")
                    protected List<Jxbv2NoteType> noteType;

                    /**
                     * Gets the value of the noteType property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * noteType property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getNoteType().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link NoteType }
                     */
                    @objid ("52f03e05-c22f-47c3-8292-4393bd8caeb6")
                    public List<Jxbv2NoteType> getNoteType() {
                        if (this.noteType == null) {
                            this.noteType = new ArrayList<>();
                        }
                        return this.noteType;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
                 * &lt;/sequence>
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("5b9a6ce3-e465-4bb2-88c8-784a79b8efc2")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "tagType"
                })
                public static class Jxbv2TagTypes {
                    @objid ("4d3fe3a6-0427-4135-a6bb-6d75056c9b3f")
                    @XmlElement (name = "TagType")
                    protected List<Jxbv2TagType> tagType;

                    /**
                     * Gets the value of the tagType property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * tagType property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getTagType().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link TagType }
                     */
                    @objid ("e42913ea-9479-41e5-9bfc-65e248a5638d")
                    public List<Jxbv2TagType> getTagType() {
                        if (this.tagType == null) {
                            this.tagType = new ArrayList<>();
                        }
                        return this.tagType;
                    }

                }

            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             * &lt;element name="Icon" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="Image" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;choice minOccurs="0">
             * &lt;element name="PropertyTable" type="{}_PropertyTableDefinition" minOccurs="0"/>
             * &lt;element name="TagTypes" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/choice>
             * &lt;element name="NoteTypes" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="ExternDocumentTypes" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="Constraints" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="Constraint" type="{}_LinkConstraint" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;element name="Extensions" minOccurs="0">
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * &lt;/element>
             * &lt;/sequence>
             * &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="metaclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="owner-stereotype" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="is-hidden" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="uid" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="is-abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("d4f4d1e6-a77f-4386-8202-6d0021d80d96")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "", propOrder = {
                    "description",
                    "icon",
                    "image",
                    "propertyTable",
                    "tagTypes",
                    "noteTypes",
                    "externDocumentTypes",
                    "constraints",
                    "extensions"
            })
            public static class Jxbv2Stereotype {
                @objid ("d5dab181-6f45-45a8-b0a6-7756d02f643b")
                @XmlAttribute (name = "name", required = true)
                protected String name;

                @objid ("e6ef3891-9881-430c-9e4d-4d4a0006b2ba")
                @XmlAttribute (name = "metaclass", required = true)
                protected String metaclass;

                @objid ("ed952661-5c19-4349-988c-7940d62cdf3f")
                @XmlAttribute (name = "owner-stereotype")
                protected String ownerStereotype;

                @objid ("b679ebb3-d983-4a17-9a3d-fb95cca2d352")
                @XmlAttribute (name = "is-hidden")
                protected String isHidden;

                @objid ("4a5f5ce0-ca11-4734-9644-b0932773e200")
                @XmlAttribute (name = "label")
                protected String label;

                @objid ("ab3da30e-f09a-40a6-b277-a07a1a960e8c")
                @XmlAttribute (name = "uid")
                protected String uid;

                @objid ("8d06442b-e679-4a01-8689-6a680a94e191")
                @XmlElement (name = "Description")
                protected String description;

                @objid ("1da9963f-4090-445d-a6c6-2b7fd7e3baff")
                @XmlAttribute (name = "is-abstract")
                protected Boolean isAbstract;

                @objid ("4862e5ff-4c78-4eea-b753-0cd81b022787")
                @XmlElement (name = "Icon")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Icon icon;

                @objid ("c4cd088e-999d-4730-b4fc-aa86876b65ca")
                @XmlElement (name = "Image")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Image image;

                @objid ("bbe5a4fd-016b-4db2-b8f1-d035cd4b001c")
                @XmlElement (name = "PropertyTable")
                protected Jxbv2PropertyTableDefinition propertyTable;

                @objid ("37f4dc5f-c7ea-4d20-bcf0-993c588b02b8")
                @XmlElement (name = "TagTypes")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2TagTypes tagTypes;

                @objid ("7600ec2c-39cf-4261-b431-3fd5a1ad2330")
                @XmlElement (name = "NoteTypes")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2NoteTypes noteTypes;

                @objid ("dde06568-b26b-4a17-a183-21941f8a2796")
                @XmlElement (name = "ExternDocumentTypes")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2ExternDocumentTypes externDocumentTypes;

                @objid ("047de6e4-7b0e-47e8-ab52-d8814976c4f8")
                @XmlElement (name = "Constraints")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Constraints constraints;

                @objid ("2a662ed3-0b82-47d4-9766-4a28e10def82")
                @XmlElement (name = "Extensions")
                protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Extensions extensions;

                /**
                 * Gets the value of the icon property.
                 * @return possible object is {@link Module.Profiles.Profile.Stereotype.Icon }
                 */
                @objid ("5a28a1e0-bfc8-4d48-88ae-d2f1f9a2d524")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Icon getIcon() {
                    return this.icon;
                }

                /**
                 * Sets the value of the icon property.
                 * @param value allowed object is {@link Module.Profiles.Profile.Stereotype.Icon }
                 */
                @objid ("e92bdf32-6445-47ba-8b88-9cd0a0061c06")
                public void setIcon(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Icon value) {
                    this.icon = value;
                }

                /**
                 * Gets the value of the image property.
                 * @return possible object is {@link Module.Profiles.Profile.Stereotype.Image }
                 */
                @objid ("2e804c47-724a-4def-aaf2-4b98d5395c2d")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Image getImage() {
                    return this.image;
                }

                /**
                 * Sets the value of the image property.
                 * @param value allowed object is {@link Module.Profiles.Profile.Stereotype.Image }
                 */
                @objid ("ebeb5021-e234-4d04-ae27-77ec1ed81cb2")
                public void setImage(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Image value) {
                    this.image = value;
                }

                /**
                 * Gets the value of the propertyTable property.
                 * @return possible object is {@link PropertyTableDefinition }
                 */
                @objid ("f2c4e74b-a64b-4348-a1c1-02a808316d85")
                public Jxbv2PropertyTableDefinition getPropertyTable() {
                    return this.propertyTable;
                }

                /**
                 * Sets the value of the propertyTable property.
                 * @param value allowed object is {@link PropertyTableDefinition }
                 */
                @objid ("0f1fa218-0852-4e02-9175-b198a07eb453")
                public void setPropertyTable(Jxbv2PropertyTableDefinition value) {
                    this.propertyTable = value;
                }

                /**
                 * Gets the value of the tagTypes property.
                 * @return possible object is {@link Module.Profiles.Profile.Stereotype.TagTypes }
                 */
                @objid ("d3d74fa0-5461-4402-86a8-8a7ef317982a")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2TagTypes getTagTypes() {
                    return this.tagTypes;
                }

                /**
                 * Sets the value of the tagTypes property.
                 * @param value allowed object is {@link Module.Profiles.Profile.Stereotype.TagTypes }
                 */
                @objid ("f87277fa-2ec6-45f3-b5b4-3ada6e66d068")
                public void setTagTypes(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2TagTypes value) {
                    this.tagTypes = value;
                }

                /**
                 * Gets the value of the noteTypes property.
                 * @return possible object is {@link Module.Profiles.Profile.Stereotype.NoteTypes }
                 */
                @objid ("0b87fc7a-61e0-46f8-b27e-4b88a1bc66e7")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2NoteTypes getNoteTypes() {
                    return this.noteTypes;
                }

                /**
                 * Sets the value of the noteTypes property.
                 * @param value allowed object is {@link Module.Profiles.Profile.Stereotype.NoteTypes }
                 */
                @objid ("f3fd522c-91f2-4071-a0c4-df37c557897e")
                public void setNoteTypes(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2NoteTypes value) {
                    this.noteTypes = value;
                }

                /**
                 * Gets the value of the externDocumentTypes property.
                 * @return possible object is {@link Module.Profiles.Profile.Stereotype.ExternDocumentTypes }
                 */
                @objid ("4602ea34-0826-47e2-aee5-a935aed438fd")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2ExternDocumentTypes getExternDocumentTypes() {
                    return this.externDocumentTypes;
                }

                /**
                 * Sets the value of the externDocumentTypes property.
                 * @param value allowed object is {@link Module.Profiles.Profile.Stereotype.ExternDocumentTypes }
                 */
                @objid ("88c2a8c0-33f5-4819-ab38-36e571fde931")
                public void setExternDocumentTypes(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2ExternDocumentTypes value) {
                    this.externDocumentTypes = value;
                }

                /**
                 * Gets the value of the name property.
                 * @return possible object is {@link String }
                 */
                @objid ("2be21bd5-d8dd-4352-9df4-ad9733dd4257")
                public String getName() {
                    return this.name;
                }

                /**
                 * Sets the value of the name property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("411d810f-1942-4499-88aa-323cc891c535")
                public void setName(String value) {
                    this.name = value;
                }

                /**
                 * Gets the value of the metaclass property.
                 * @return possible object is {@link String }
                 */
                @objid ("5b028ff6-6349-4e36-a89d-93c2f11862f7")
                public String getMetaclass() {
                    return this.metaclass;
                }

                /**
                 * Sets the value of the metaclass property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("e7cb24a7-1308-4a34-8657-f32b3f7ec24d")
                public void setMetaclass(String value) {
                    this.metaclass = value;
                }

                /**
                 * Gets the value of the ownerStereotype property.
                 * @return possible object is {@link String }
                 */
                @objid ("53cb6bb1-5782-4254-bf06-86a6d8dd7abd")
                public String getOwnerStereotype() {
                    return this.ownerStereotype;
                }

                /**
                 * Sets the value of the ownerStereotype property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("e983256c-0ffb-4d95-ae1a-e1fa4ac88a1e")
                public void setOwnerStereotype(String value) {
                    this.ownerStereotype = value;
                }

                /**
                 * Gets the value of the isHidden property.
                 * @return possible object is {@link String }
                 */
                @objid ("d509fb29-70f5-4962-af72-c0e7c567c3e9")
                public String getIsHidden() {
                    return this.isHidden;
                }

                /**
                 * Sets the value of the isHidden property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("77238813-02dc-4467-a5dc-3643f4e39ed0")
                public void setIsHidden(String value) {
                    this.isHidden = value;
                }

                /**
                 * Gets the value of the label property.
                 * @return possible object is {@link String }
                 */
                @objid ("71a934cf-ce42-466e-8056-4e080018be15")
                public String getLabel() {
                    return this.label;
                }

                /**
                 * Sets the value of the label property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("ddc311a7-c8e0-4bc3-9265-4188fb859d0b")
                public void setLabel(String value) {
                    this.label = value;
                }

                /**
                 * Gets the value of the uid property.
                 * @return possible object is {@link String }
                 */
                @objid ("10170639-10ae-4767-950f-d406ddb9349b")
                public String getUid() {
                    return this.uid;
                }

                /**
                 * Sets the value of the uid property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("b0e3a3b4-ffb9-49e4-8288-66624bed4393")
                public void setUid(String value) {
                    this.uid = value;
                }

                /**
                 * Gets the value of the description property.
                 * @return possible object is {@link String }
                 */
                @objid ("bc23ee3d-94d7-4a48-b1e7-ceb5d35e2eeb")
                public String getDescription() {
                    return this.description;
                }

                /**
                 * Sets the value of the description property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("a9a462ea-a68d-4346-a37a-1307d079020f")
                public void setDescription(String value) {
                    this.description = value;
                }

                /**
                 * Gets the value of the constraints property.
                 * @return possible object is {@link Module.Profiles.Profile.Stereotype.Constraints }
                 */
                @objid ("5eebbc61-d491-4e2d-ac47-405a4093dd34")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Constraints getConstraints() {
                    return this.constraints;
                }

                /**
                 * Sets the value of the constraints property.
                 * @param value allowed object is {@link Module.Profiles.Profile.Stereotype.Constraints }
                 */
                @objid ("6a9a3464-8b6a-4b55-a92b-4b408bd04061")
                public void setConstraints(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Constraints value) {
                    this.constraints = value;
                }

                /**
                 * Gets the value of the extensions property.
                 * @return possible object is {@link Module.Profiles.Profile.Stereotype.Extensions }
                 */
                @objid ("1bfec2ec-f629-4f39-bbfb-d3c09f8d5d01")
                public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Extensions getExtensions() {
                    return this.extensions;
                }

                /**
                 * Sets the value of the extensions property.
                 * @param value allowed object is {@link Module.Profiles.Profile.Stereotype.Extensions }
                 */
                @objid ("314144c7-9265-4c94-8f80-88ad47aaf5a2")
                public void setExtensions(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Profiles.Jxbv2Profile.Jxbv2Stereotype.Jxbv2Extensions value) {
                    this.extensions = value;
                }

                /**
                 * Gets the value of the isAbstract property.
                 * @return possible object is {@link Boolean }
                 */
                @objid ("c12ad585-da97-42b9-a623-cfa8dcb5e076")
                public boolean isIsAbstract() {
                    if (this.isAbstract == null) {
                        return false;
                    } else {
                        return this.isAbstract;
                    }
                }

                /**
                 * Sets the value of the isAbstract property.
                 * @param value allowed object is {@link Boolean }
                 */
                @objid ("93364dcf-883c-4001-985e-b3d30706603d")
                public void setIsAbstract(Boolean value) {
                    this.isAbstract = value;
                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="ExternDocumentType" type="{}_ExternDocumentType" maxOccurs="unbounded" minOccurs="0"/>
                 * &lt;/sequence>
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("b8e2b4c5-3540-4940-b604-5a408e202b74")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "externDocumentType"
                })
                public static class Jxbv2ExternDocumentTypes {
                    @objid ("dd70cc1a-4228-4e74-b27e-f72ca80ce007")
                    @XmlElement (name = "ExternDocumentType")
                    protected List<Jxbv2ExternDocumentType> externDocumentType;

                    /**
                     * Gets the value of the externDocumentType property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * externDocumentType property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getExternDocumentType().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link ExternDocumentType }
                     */
                    @objid ("fc3bfe89-7d4c-4c01-8c0d-d01e09098864")
                    public List<Jxbv2ExternDocumentType> getExternDocumentType() {
                        if (this.externDocumentType == null) {
                            this.externDocumentType = new ArrayList<>();
                        }
                        return this.externDocumentType;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("0e716c2d-6206-408d-ac8a-f0bcffc89984")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "")
                public static class Jxbv2Icon {
                    @objid ("c85c64d0-6f9f-48df-bf39-efcbb552e501")
                    @XmlAttribute (name = "path", required = true)
                    protected String path;

                    /**
                     * Gets the value of the path property.
                     * @return possible object is {@link String }
                     */
                    @objid ("8b6714a9-0234-4f24-9a00-9eb2166812f3")
                    public String getPath() {
                        return this.path;
                    }

                    /**
                     * Sets the value of the path property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("8ed72e90-0f9f-4774-a4da-362ef14467b0")
                    public void setPath(String value) {
                        this.path = value;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("0b9e726a-04ff-4076-a46f-a785de3ed861")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "")
                public static class Jxbv2Image {
                    @objid ("5070cfc7-d804-4fab-9e01-9a98d66dfa57")
                    @XmlAttribute (name = "path", required = true)
                    protected String path;

                    /**
                     * Gets the value of the path property.
                     * @return possible object is {@link String }
                     */
                    @objid ("225ad0e3-c399-4af1-9b37-996b8cc53ad5")
                    public String getPath() {
                        return this.path;
                    }

                    /**
                     * Sets the value of the path property.
                     * @param value allowed object is {@link String }
                     */
                    @objid ("93344058-2d3d-4273-ab93-e9a61d4a252d")
                    public void setPath(String value) {
                        this.path = value;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="NoteType" type="{}_NoteType" maxOccurs="unbounded" minOccurs="0"/>
                 * &lt;/sequence>
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("61ded4f1-783b-43d1-8459-449be6337e8b")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "noteType"
                })
                public static class Jxbv2NoteTypes {
                    @objid ("8951208a-abe2-4af4-ae8f-a2e9cd207534")
                    @XmlElement (name = "NoteType")
                    protected List<Jxbv2NoteType> noteType;

                    /**
                     * Gets the value of the noteType property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * noteType property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getNoteType().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link NoteType }
                     */
                    @objid ("9f74e6f4-e8cf-470a-b790-ce20f70e7dc4")
                    public List<Jxbv2NoteType> getNoteType() {
                        if (this.noteType == null) {
                            this.noteType = new ArrayList<>();
                        }
                        return this.noteType;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="TagType" type="{}_TagType" maxOccurs="unbounded" minOccurs="0"/>
                 * &lt;/sequence>
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("c29d18d3-78b1-4905-ae63-1b7613183a21")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "tagType"
                })
                public static class Jxbv2TagTypes {
                    @objid ("ac6e68ac-09e7-4223-ac29-4dd4233ce174")
                    @XmlElement (name = "TagType")
                    protected List<Jxbv2TagType> tagType;

                    /**
                     * Gets the value of the tagType property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * tagType property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getTagType().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link TagType }
                     */
                    @objid ("7e4621ea-3b53-4937-9d6b-af223c7a1f04")
                    public List<Jxbv2TagType> getTagType() {
                        if (this.tagType == null) {
                            this.tagType = new ArrayList<>();
                        }
                        return this.tagType;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="Constraint" type="{}_LinkConstraint" maxOccurs="unbounded" minOccurs="0"/>
                 * &lt;/sequence>
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("46124078-e7d5-4f66-a1fc-a25953e80154")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "constraint"
                })
                public static class Jxbv2Constraints {
                    @objid ("1ed19e2a-e9f7-45f3-ba7e-e4517748a3c5")
                    @XmlElement (name = "Constraint")
                    protected List<Jxbv2LinkConstraint> constraint;

                    /**
                     * Gets the value of the constraint property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * constraint property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getConstraint().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link LinkConstraint }
                     */
                    @objid ("01e70554-e24e-4e68-a028-fa9f57e3883d")
                    public List<Jxbv2LinkConstraint> getConstraint() {
                        if (this.constraint == null) {
                            this.constraint = new ArrayList<>();
                        }
                        return this.constraint;
                    }

                }

                /**
                 * <p>
                 * Java class for anonymous complex type.
                 * 
                 * <p>
                 * The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 * &lt;complexContent>
                 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 * &lt;sequence>
                 * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
                 * &lt;/sequence>
                 * &lt;/restriction>
                 * &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 */
                @objid ("d3d28520-5270-46bd-b7dd-8839c28bd6fd")
                @XmlAccessorType (XmlAccessType.FIELD)
                @XmlType (name = "", propOrder = {
                        "stereotypeRef"
                })
                public static class Jxbv2Extensions {
                    @objid ("d358d769-9909-4ae3-9fd2-b25948894322")
                    @XmlElement (name = "StereotypeRef")
                    protected List<Jxbv2StereotypeRef> stereotypeRef;

                    /**
                     * Gets the value of the stereotypeRef property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                     * stereotypeRef property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * 
                     * <pre>
                     * getStereotypeRef().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list {@link StereotypeRef }
                     */
                    @objid ("67fada7d-1756-4c05-8cac-985f57931501")
                    public List<Jxbv2StereotypeRef> getStereotypeRef() {
                        if (this.stereotypeRef == null) {
                            this.stereotypeRef = new ArrayList<>();
                        }
                        return this.stereotypeRef;
                    }

                }

            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;sequence>
             * &lt;element name="StereotypeRef" type="{}_StereotypeRef" maxOccurs="unbounded" minOccurs="0"/>
             * &lt;/sequence>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("73b95e50-3465-4a1d-a00f-17307a419920")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "", propOrder = {
                    "stereotypeRef"
            })
            public static class Jxbv2Extensions {
                @objid ("c8427130-f512-4acf-9375-fc5e734e1d8b")
                @XmlElement (name = "StereotypeRef")
                protected List<Jxbv2StereotypeRef> stereotypeRef;

                /**
                 * Gets the value of the stereotypeRef property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
                 * stereotypeRef property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * 
                 * <pre>
                 * getStereotypeRef().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list {@link StereotypeRef }
                 */
                @objid ("7f5047ba-74b0-4e80-84bd-9e04ca735eeb")
                public List<Jxbv2StereotypeRef> getStereotypeRef() {
                    if (this.stereotypeRef == null) {
                        this.stereotypeRef = new ArrayList<>();
                    }
                    return this.stereotypeRef;
                }

            }

        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="PropertyType" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;extension base="{}_PropertyType">
     * &lt;/extension>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("65e47d1a-5308-4881-a884-f0db719c6448")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "", propOrder = {
            "propertyType"
    })
    public static class Jxbv2PropertyTypes {
        @objid ("9d8c4c6b-34ba-4187-b784-e0bc23f88f5a")
        @XmlElement (name = "PropertyType")
        protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2PropertyTypes.Jxbv2PropertyType> propertyType;

        /**
         * Gets the value of the propertyType property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the propertyType
         * property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getPropertyType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Module.PropertyTypes.PropertyType }
         */
        @objid ("06d04d5f-a2e1-4733-974a-e2745fa3dece")
        public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2PropertyTypes.Jxbv2PropertyType> getPropertyType() {
            if (this.propertyType == null) {
                this.propertyType = new ArrayList<>();
            }
            return this.propertyType;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;extension base="{}_PropertyType">
         * &lt;/extension>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("726948cf-fade-4b06-bca2-dd81c4dcf4bd")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "")
        public static class Jxbv2PropertyType extends org.modelio.gproject.data.module.jaxbv2.Jxbv2PropertyType {
        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Styles" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Style" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="DocFiles" type="{}_MultiPathes" minOccurs="0"/>
     * &lt;element name="Macros" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Macro" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="Patterns" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="Pattern" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;element name="DocTemplates" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="DocTemplate" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
     * &lt;attribute name="lang" default="en">
     * &lt;simpleType>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     * &lt;enumeration value="en"/>
     * &lt;enumeration value="fr"/>
     * &lt;/restriction>
     * &lt;/simpleType>
     * &lt;/attribute>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("6ce909d3-700d-4ecc-863a-845fdfae5c54")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "", propOrder = {
            "styles",
            "docFiles",
            "macros",
            "patterns",
            "docTemplates"
    })
    public static class Jxbv2Resources {
        @objid ("cde4c128-695c-46d6-aefc-2e678c000f05")
        @XmlElement (name = "Styles")
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Styles styles;

        @objid ("a3816a79-c0d7-431f-8ba0-b16af17f4664")
        @XmlElement (name = "DocFiles")
        protected Jxbv2MultiPathes docFiles;

        @objid ("eefe097b-21f5-4a62-ae52-b4f26900e096")
        @XmlElement (name = "Macros")
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Macros macros;

        @objid ("008dec81-9260-452a-9f1c-3aefb5f76470")
        @XmlElement (name = "Patterns")
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Patterns patterns;

        @objid ("8a023bc8-08b7-43b2-8075-3cc447d82d3b")
        @XmlElement (name = "DocTemplates")
        protected org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates docTemplates;

        /**
         * Gets the value of the styles property.
         * @return possible object is {@link Module.Resources.Styles }
         */
        @objid ("3293e14a-696c-4a2a-a092-a9c9aa89441b")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Styles getStyles() {
            return this.styles;
        }

        /**
         * Sets the value of the styles property.
         * @param value allowed object is {@link Module.Resources.Styles }
         */
        @objid ("ad5178ad-492d-4022-b12f-ad4631f2ae54")
        public void setStyles(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Styles value) {
            this.styles = value;
        }

        /**
         * Gets the value of the docFiles property.
         * @return possible object is {@link MultiPathes }
         */
        @objid ("19786d94-aea1-4d0f-a51b-2eb86ed54281")
        public Jxbv2MultiPathes getDocFiles() {
            return this.docFiles;
        }

        /**
         * Sets the value of the docFiles property.
         * @param value allowed object is {@link MultiPathes }
         */
        @objid ("f3cffef9-c0a7-4eda-b1b0-b9cb59402fe5")
        public void setDocFiles(Jxbv2MultiPathes value) {
            this.docFiles = value;
        }

        /**
         * Gets the value of the macros property.
         * @return possible object is {@link Module.Resources.Macros }
         */
        @objid ("c2ed2644-d24f-48b7-aeff-b43e66c79ba5")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Macros getMacros() {
            return this.macros;
        }

        /**
         * Sets the value of the macros property.
         * @param value allowed object is {@link Module.Resources.Macros }
         */
        @objid ("7f5cfdbb-0d3f-47a1-abab-98f5db6c921f")
        public void setMacros(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Macros value) {
            this.macros = value;
        }

        /**
         * Gets the value of the patterns property.
         * @return possible object is {@link Module.Resources.Patterns }
         */
        @objid ("02151f8b-3dad-473f-abd2-9d79dc269fb8")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Patterns getPatterns() {
            return this.patterns;
        }

        /**
         * Sets the value of the patterns property.
         * @param value allowed object is {@link Module.Resources.Patterns }
         */
        @objid ("8f476447-faba-4127-baa7-e2b9ce207ff2")
        public void setPatterns(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Patterns value) {
            this.patterns = value;
        }

        /**
         * Gets the value of the docTemplates property.
         * @return possible object is {@link Module.Resources.DocTemplates }
         */
        @objid ("abff6dda-7e75-4de0-b653-73be688d10a3")
        public org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates getDocTemplates() {
            return this.docTemplates;
        }

        /**
         * Sets the value of the docTemplates property.
         * @param value allowed object is {@link Module.Resources.DocTemplates }
         */
        @objid ("d7f4bc72-763a-425a-9dc9-03129d28a640")
        public void setDocTemplates(org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates value) {
            this.docTemplates = value;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Style" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("90d042af-529e-4257-b511-47fe0c8ac028")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "style"
        })
        public static class Jxbv2Styles {
            @objid ("6d3ac25f-4796-4c42-a7e8-2ab751999262")
            @XmlElement (name = "Style")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Styles.Jxbv2Style> style;

            /**
             * Gets the value of the style property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the style
             * property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getStyle().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Module.Resources.Styles.Style }
             */
            @objid ("34a73390-662c-428f-8f28-a6d6bdbaf446")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Styles.Jxbv2Style> getStyle() {
                if (this.style == null) {
                    this.style = new ArrayList<>();
                }
                return this.style;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("2c29e60a-ea7d-4d69-b822-7a1ea1bfec16")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "")
            public static class Jxbv2Style {
                @objid ("d5a9eaba-3273-4787-b0d8-6a9116cff5e6")
                @XmlAttribute (name = "id", required = true)
                protected String id;

                @objid ("4dc85fea-e16d-4de2-ae32-85769ea29ed6")
                @XmlAttribute (name = "path", required = true)
                protected String path;

                /**
                 * Gets the value of the id property.
                 * @return possible object is {@link String }
                 */
                @objid ("a94fa316-f4a7-4bf4-ab72-8791d2773461")
                public String getId() {
                    return this.id;
                }

                /**
                 * Sets the value of the id property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("10d1f957-8c99-4f1f-beda-adb0dcf05026")
                public void setId(String value) {
                    this.id = value;
                }

                /**
                 * Gets the value of the path property.
                 * @return possible object is {@link String }
                 */
                @objid ("b080bfc0-eec9-4fdc-820c-2fe346d68bb5")
                public String getPath() {
                    return this.path;
                }

                /**
                 * Sets the value of the path property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("7bc84ceb-f051-46a6-aebf-a9fc8b704b97")
                public void setPath(String value) {
                    this.path = value;
                }

            }

        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="DocTemplate" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
         * &lt;attribute name="lang" default="en">
         * &lt;simpleType>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         * &lt;enumeration value="en"/>
         * &lt;enumeration value="fr"/>
         * &lt;/restriction>
         * &lt;/simpleType>
         * &lt;/attribute>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("7e053760-31a1-47dc-a22b-0e61dce5c5ed")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "docTemplate"
        })
        public static class Jxbv2DocTemplates {
            @objid ("12f8864e-ecb2-4b7e-984e-b35828f7049a")
            @XmlElement (name = "DocTemplate")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates.Jxbv2DocTemplate> docTemplate;

            /**
             * Gets the value of the docTemplate property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
             * docTemplate property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getDocTemplate().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Module.Resources.DocTemplates.DocTemplate }
             */
            @objid ("fbe688c6-50ff-4ef1-bd47-416bfe80b262")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2DocTemplates.Jxbv2DocTemplate> getDocTemplate() {
                if (this.docTemplate == null) {
                    this.docTemplate = new ArrayList<>();
                }
                return this.docTemplate;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
             * &lt;attribute name="lang" default="en">
             * &lt;simpleType>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             * &lt;enumeration value="en"/>
             * &lt;enumeration value="fr"/>
             * &lt;/restriction>
             * &lt;/simpleType>
             * &lt;/attribute>
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("00c8c340-7e21-44bb-aab8-c3901b04fe25")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "")
            public static class Jxbv2DocTemplate {
                @objid ("1c209838-572f-400a-9696-bec1fe50579e")
                @XmlAttribute (name = "id", required = true)
                protected String id;

                @objid ("ee8fe581-6b2b-40d6-9607-17833048fa66")
                @XmlAttribute (name = "path", required = true)
                protected String path;

                @objid ("9bea708c-15f2-4097-a47d-bd7b0fec2b9c")
                @XmlAttribute (name = "published")
                protected Boolean published;

                @objid ("42b439bb-1a6b-4f93-ba66-dec17394ab80")
                @XmlAttribute (name = "lang")
                protected String lang;

                /**
                 * Gets the value of the id property.
                 * @return possible object is {@link String }
                 */
                @objid ("0f9bfb26-29b0-470f-a0df-ebc9cfb81eb8")
                public String getId() {
                    return this.id;
                }

                /**
                 * Sets the value of the id property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("79bdc421-bd44-4e58-bbf6-0bd6a78c51fe")
                public void setId(String value) {
                    this.id = value;
                }

                /**
                 * Gets the value of the path property.
                 * @return possible object is {@link String }
                 */
                @objid ("0f1afb67-d5ea-40fc-9098-a51d3ff1551f")
                public String getPath() {
                    return this.path;
                }

                /**
                 * Sets the value of the path property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("bca7da14-27c0-405b-93ae-547df251ebdb")
                public void setPath(String value) {
                    this.path = value;
                }

                /**
                 * Gets the value of the published property.
                 * @return possible object is {@link Boolean }
                 */
                @objid ("7d539286-773f-45df-aab8-a3fb26f705b7")
                public boolean isPublished() {
                    if (this.published == null) {
                        return false;
                    } else {
                        return this.published;
                    }
                }

                /**
                 * Sets the value of the published property.
                 * @param value allowed object is {@link Boolean }
                 */
                @objid ("fd6faa42-6f76-403e-8cef-ac2978dd6d8c")
                public void setPublished(Boolean value) {
                    this.published = value;
                }

                /**
                 * Gets the value of the lang property.
                 * @return possible object is {@link String }
                 */
                @objid ("c52fb4f9-53be-45f4-9d4c-2c698a3ba966")
                public String getLang() {
                    if (this.lang == null) {
                        return "en";
                    } else {
                        return this.lang;
                    }
                }

                /**
                 * Sets the value of the lang property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("cc80a597-c9b0-40ce-832b-bc241095413a")
                public void setLang(String value) {
                    this.lang = value;
                }

            }

        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Macro" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("8f7059bc-cb02-4505-8141-4ecd27e21e52")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "macro"
        })
        public static class Jxbv2Macros {
            @objid ("b840f577-1496-45c4-9324-9f9b16c30235")
            @XmlElement (name = "Macro")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Macros.Jxbv2Macro> macro;

            /**
             * Gets the value of the macro property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the macro
             * property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getMacro().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Module.Resources.Macros.Macro }
             */
            @objid ("f7e1944c-5b45-43c0-8075-ce8f788ed6c9")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Macros.Jxbv2Macro> getMacro() {
                if (this.macro == null) {
                    this.macro = new ArrayList<>();
                }
                return this.macro;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("827ae442-5f68-4c32-836a-9dbe3880b5d7")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "")
            public static class Jxbv2Macro {
                @objid ("4dade07c-44fc-4dbb-b75c-15fea0b73b62")
                @XmlAttribute (name = "id", required = true)
                protected String id;

                @objid ("f7235b2e-904d-4e5b-8170-6c7a5e2f1169")
                @XmlAttribute (name = "path", required = true)
                protected String path;

                @objid ("4655c953-72d8-472a-9e9e-f8c596f8138e")
                @XmlAttribute (name = "published")
                protected Boolean published;

                /**
                 * Gets the value of the id property.
                 * @return possible object is {@link String }
                 */
                @objid ("d4b6caaf-9e68-4bbd-9e07-0480f951af8e")
                public String getId() {
                    return this.id;
                }

                /**
                 * Sets the value of the id property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("e7de69f4-6bcb-4522-9260-f2622f9d0223")
                public void setId(String value) {
                    this.id = value;
                }

                /**
                 * Gets the value of the path property.
                 * @return possible object is {@link String }
                 */
                @objid ("9345d05a-6256-428c-94b2-276d63aed4e7")
                public String getPath() {
                    return this.path;
                }

                /**
                 * Sets the value of the path property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("862fc39f-fe81-4538-94e7-bfdfaf9b5c57")
                public void setPath(String value) {
                    this.path = value;
                }

                /**
                 * Gets the value of the published property.
                 * @return possible object is {@link Boolean }
                 */
                @objid ("73a48c4a-4a61-464b-8550-74a8ca2dbc85")
                public boolean isPublished() {
                    if (this.published == null) {
                        return false;
                    } else {
                        return this.published;
                    }
                }

                /**
                 * Sets the value of the published property.
                 * @param value allowed object is {@link Boolean }
                 */
                @objid ("77594a67-081c-40b4-ae56-e4b0c6938220")
                public void setPublished(Boolean value) {
                    this.published = value;
                }

            }

        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;sequence>
         * &lt;element name="Pattern" maxOccurs="unbounded" minOccurs="0">
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * &lt;/element>
         * &lt;/sequence>
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("5877dac5-7401-4c8b-bf10-968cf0806979")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "", propOrder = {
                "pattern"
        })
        public static class Jxbv2Patterns {
            @objid ("7b59f57e-df72-49ca-8104-0d560b91d1a0")
            @XmlElement (name = "Pattern")
            protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Patterns.Jxbv2Pattern> pattern;

            /**
             * Gets the value of the pattern property.
             * 
             * <p>
             * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the pattern
             * property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * 
             * <pre>
             * getPattern().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list {@link Module.Resources.Patterns.Pattern }
             */
            @objid ("78c9328b-28f0-4a7a-9c27-eaf57407f219")
            public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Resources.Jxbv2Patterns.Jxbv2Pattern> getPattern() {
                if (this.pattern == null) {
                    this.pattern = new ArrayList<>();
                }
                return this.pattern;
            }

            /**
             * <p>
             * Java class for anonymous complex type.
             * 
             * <p>
             * The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             * &lt;complexContent>
             * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             * &lt;attribute name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
             * &lt;/restriction>
             * &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @objid ("13442610-cef1-4b78-9498-7d90398021a4")
            @XmlAccessorType (XmlAccessType.FIELD)
            @XmlType (name = "")
            public static class Jxbv2Pattern {
                @objid ("35cfa822-6e4d-4177-80ba-856bf718bd0b")
                @XmlAttribute (name = "id", required = true)
                protected String id;

                @objid ("e70b89bf-d13e-44bc-878c-5bec22845736")
                @XmlAttribute (name = "path", required = true)
                protected String path;

                @objid ("c91803b0-36f7-4723-97d3-425fc18b257b")
                @XmlAttribute (name = "published")
                protected Boolean published;

                /**
                 * Gets the value of the id property.
                 * @return possible object is {@link String }
                 */
                @objid ("cfb44d6e-4178-4f8b-bf84-8733e52ebec2")
                public String getId() {
                    return this.id;
                }

                /**
                 * Sets the value of the id property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("7c024515-cc97-4c96-908b-27cbb72c25f9")
                public void setId(String value) {
                    this.id = value;
                }

                /**
                 * Gets the value of the path property.
                 * @return possible object is {@link String }
                 */
                @objid ("9579c422-a36e-4cdb-a050-03d534adf50b")
                public String getPath() {
                    return this.path;
                }

                /**
                 * Sets the value of the path property.
                 * @param value allowed object is {@link String }
                 */
                @objid ("e2c78e50-a142-4543-af76-169bb692ff9c")
                public void setPath(String value) {
                    this.path = value;
                }

                /**
                 * Gets the value of the published property.
                 * @return possible object is {@link Boolean }
                 */
                @objid ("088b63f1-c221-412e-8f7f-fe638bb1d22b")
                public boolean isPublished() {
                    if (this.published == null) {
                        return false;
                    } else {
                        return this.published;
                    }
                }

                /**
                 * Sets the value of the published property.
                 * @param value allowed object is {@link Boolean }
                 */
                @objid ("dc1382cd-5af1-41c3-b42b-049ce2e53829")
                public void setPublished(Boolean value) {
                    this.published = value;
                }

            }

        }

    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;sequence>
     * &lt;element name="MetamodelFragment" maxOccurs="unbounded" minOccurs="0">
     * &lt;complexType>
     * &lt;complexContent>
     * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="vendor" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;attribute name="vendor-version" type="{http://www.w3.org/2001/XMLSchema}string" />
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * &lt;/element>
     * &lt;/sequence>
     * &lt;/restriction>
     * &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @objid ("58e61148-f0c9-41f2-9f1d-89350928eaf2")
    @XmlAccessorType (XmlAccessType.FIELD)
    @XmlType (name = "", propOrder = {
            "metamodelFragment"
    })
    public static class Jxbv2MetamodelFragments {
        @objid ("9064eecd-a07a-4554-ba6a-bbe73f4fe00b")
        @XmlElement (name = "MetamodelFragment")
        protected List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2MetamodelFragments.Jxbv2MetamodelFragment> metamodelFragment;

        /**
         * Gets the value of the metamodelFragment property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
         * metamodelFragment property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getMetamodelFragment().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link Module.MetamodelFragments.MetamodelFragment }
         */
        @objid ("67f1861a-1495-4dea-b60c-78550f7a467f")
        public List<org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2MetamodelFragments.Jxbv2MetamodelFragment> getMetamodelFragment() {
            if (this.metamodelFragment == null) {
                this.metamodelFragment = new ArrayList<>();
            }
            return this.metamodelFragment;
        }

        /**
         * <p>
         * Java class for anonymous complex type.
         * 
         * <p>
         * The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         * &lt;complexContent>
         * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         * &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="vendor" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;attribute name="vendor-version" type="{http://www.w3.org/2001/XMLSchema}string" />
         * &lt;/restriction>
         * &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @objid ("75a5ac1e-f6ac-47d4-bbde-d63b083ec0ab")
        @XmlAccessorType (XmlAccessType.FIELD)
        @XmlType (name = "")
        public static class Jxbv2MetamodelFragment {
            @objid ("9c134811-09f5-4eb5-bad0-3153fa16d4f5")
            @XmlAttribute (name = "id", required = true)
            protected String id;

            @objid ("256371a9-e79e-4108-a597-b4c20d8a35d9")
            @XmlAttribute (name = "version", required = true)
            protected String version;

            @objid ("e3f13935-469e-45bd-90d4-d8fda82c96bb")
            @XmlAttribute (name = "class", required = true)
            protected String clazz;

            @objid ("3ab6140a-2a2c-4161-99ee-f51d604c4771")
            @XmlAttribute (name = "vendor")
            protected String vendor;

            @objid ("77a63ff9-6e66-4e28-80e5-6ce1fd6448a9")
            @XmlAttribute (name = "vendor-version")
            protected String vendorVersion;

            /**
             * Gets the value of the id property.
             * @return possible object is {@link String }
             */
            @objid ("adccd5a8-3824-4692-9a85-760e70240b9a")
            public String getId() {
                return this.id;
            }

            /**
             * Sets the value of the id property.
             * @param value allowed object is {@link String }
             */
            @objid ("374e4eb8-fc34-419d-835a-61d6cb8dfe48")
            public void setId(String value) {
                this.id = value;
            }

            /**
             * Gets the value of the version property.
             * @return possible object is {@link String }
             */
            @objid ("bfae1e44-1f31-4480-ae26-a07d01869373")
            public String getVersion() {
                return this.version;
            }

            /**
             * Sets the value of the version property.
             * @param value allowed object is {@link String }
             */
            @objid ("34efb21e-4fd9-45d3-a3d9-d7d682ee4de5")
            public void setVersion(String value) {
                this.version = value;
            }

            /**
             * Gets the value of the clazz property.
             * @return possible object is {@link String }
             */
            @objid ("267aafd0-1a56-4cb6-a7c9-0c3be88e5e28")
            public String getClazz() {
                return this.clazz;
            }

            /**
             * Sets the value of the clazz property.
             * @param value allowed object is {@link String }
             */
            @objid ("6890eb4f-74e1-4384-9187-3c33b4cfe1ea")
            public void setClazz(String value) {
                this.clazz = value;
            }

            /**
             * Gets the value of the vendor property.
             * @return possible object is {@link String }
             */
            @objid ("af9b10f7-656d-4916-b020-7b6e26e5c9a9")
            public String getVendor() {
                return this.vendor;
            }

            /**
             * Sets the value of the vendor property.
             * @param value allowed object is {@link String }
             */
            @objid ("53c562fb-d777-404b-b4eb-a8002db60d31")
            public void setVendor(String value) {
                this.vendor = value;
            }

            /**
             * Gets the value of the vendorVersion property.
             * @return possible object is {@link String }
             */
            @objid ("a4759dd8-2a96-44e4-99b6-7ba94030cb93")
            public String getVendorVersion() {
                return this.vendorVersion;
            }

            /**
             * Sets the value of the vendorVersion property.
             * @param value allowed object is {@link String }
             */
            @objid ("0954b662-0832-49ef-8624-21cdead29c6e")
            public void setVendorVersion(String value) {
                this.vendorVersion = value;
            }

        }

    }

}
