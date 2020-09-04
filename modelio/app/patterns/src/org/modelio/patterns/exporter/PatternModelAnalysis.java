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

package org.modelio.patterns.exporter;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("a3e513ee-7bbb-47ef-9387-379f8dce7c06")
public class PatternModelAnalysis {
    @objid ("8100b63e-453a-4ab7-8b67-aff43f40eae4")
    private List<ModelElement> elementParameters = new ArrayList<>();

    @objid ("15260b1c-c049-48fc-ba2b-dee7576a44f0")
    private List<ModelElement> rootParameters = new ArrayList<>();

    @objid ("96a99e5d-3ae8-4d4e-9d22-649b30e093b0")
    private List<ModuleComponent> moduleDependencies = new ArrayList<>();

    @objid ("caa16d2e-bdc8-4ca5-adb3-c251639f7dd4")
    private List<ModelElement> ramcDependencies = new ArrayList<>();

    @objid ("0f0b481f-bb4e-4cbf-8c81-8fb6ff21bdad")
    private List<ReportStringParameter> stringParameters = new ArrayList<>();

    @objid ("a99c7794-5d43-476c-bcd2-9ec770137c09")
    public List<ModelElement> getElementParameters() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.elementParameters;
    }

    @objid ("c97f8986-9c41-42bd-b5a7-40fba2245503")
    public List<ModelElement> getRootParameters() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.rootParameters;
    }

    @objid ("8bccbea0-0e78-4f1a-86ce-f2ecbf144a3d")
    public List<ModuleComponent> getModuleDependencies() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.moduleDependencies;
    }

    @objid ("e2371d4f-9c2c-4678-8cef-9c3e3de62cd8")
    public List<ModelElement> getRamcDependencies() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.ramcDependencies;
    }

    @objid ("bbcad273-78c8-41cf-b5ae-67815a6cbfd5")
    public List<ReportStringParameter> getStringParameters() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.stringParameters;
    }

    @objid ("3dad9ee0-0a86-49c5-befd-2781988efea9")
    public void addElementParameter(ModelElement value) {
        this.elementParameters.add(value);
    }

    @objid ("32b546cb-c53e-455e-97b1-1475d0deee1d")
    public void addModuleDependency(ModuleComponent value) {
        this.moduleDependencies.add(value);
    }

    @objid ("5bb9e4ad-6e8b-40a2-a414-feee35585f2a")
    public void addRamcDependency(ModelElement value) {
        this.ramcDependencies.add(value);
    }

    @objid ("2f6c4bc7-93ce-4082-8bd4-bf39ba1ac3e6")
    public void addRootParameter(ModelElement value) {
        this.rootParameters.add(value);
    }

    @objid ("fcd3b883-b1bb-4fa2-a114-70bf8756855c")
    public void addStringParameter(String name, MRef mRef) {
        this.stringParameters.add(new ReportStringParameter(name, mRef));
    }

    @objid ("1fd91b89-e54a-4b6b-a5b1-a7dc225470b8")
    public static class ReportStringParameter {
        @objid ("ff05d35e-51ca-49f2-9574-860364eae7dc")
        public String name;

        @objid ("d7168273-ff08-4198-8c11-ad38fe30dd68")
        public MRef mRef;

        @objid ("8b090354-1caa-4ffe-af7b-b65b248805e8")
        public ReportStringParameter(String name, MRef mRef) {
            this.name = name;
            this.mRef = mRef;
        }

    }

}
