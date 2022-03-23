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
package org.modelio.gproject.data.ramc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.ramc.IModelComponentInfos.ExportedFile;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

@objid ("f124f692-cc2f-11e1-87f1-001ec947ccaf")
class ModelComponentInfos implements IModelComponentInfos {
    @objid ("74507b6a-cc3e-11e1-87f1-001ec947ccaf")
    private String description;

    @objid ("74507b64-cc3e-11e1-87f1-001ec947ccaf")
    private String name;

    @objid ("b31eb867-4261-4166-9f14-be37fe22b235")
    private String provider;

    @objid ("374df66e-f87d-4853-a846-5e45b7dd66fd")
    private List<VersionedItem<?>> contributingModule = new ArrayList<>();

    @objid ("887ff53e-518a-45f4-89ce-df3dab16eb60")
    private List<ExportedFile> exportedFiles = new ArrayList<>();

    @objid ("707fce92-6dc7-429a-b754-bcb5a6723995")
    private List<VersionedItem<?>> requiredMetamodelFragments = new ArrayList<>();

    @objid ("a91c04c3-6f25-4f57-ad14-5d844fe55656")
    private List<VersionedItem<?>> requiredModelComponents = new ArrayList<>();

    @objid ("b047d08f-2dc9-4298-a430-260285c24ab3")
    private List<ModelRef> roots = new ArrayList<>();

    @objid ("7d0386ee-d27d-11e1-a594-001ec947ccaf")
    private Version version;

    @objid ("b90cfb82-e950-4147-aaa3-007f5c06a82b")
    private Version modelioVersion;

    @objid ("a122b70d-242a-4ab1-915c-8d9365a80579")
    @Override
    public List<VersionedItem<?>> getContributingModules() {
        return Collections.unmodifiableList(this.contributingModule);
    }

    @objid ("a01e6602-cc36-11e1-87f1-001ec947ccaf")
    @Override
    public String getDescription() {
        return this.description;
    }

    @objid ("8f227e6f-8cd0-4eba-b242-b01aa816eef2")
    @Override
    public List<ExportedFile> getExportedFiles() {
        return Collections.unmodifiableList(this.exportedFiles);
    }

    @objid ("a01c03b2-cc36-11e1-87f1-001ec947ccaf")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("f2f23680-7d68-47c7-89df-0a3e126fc914")
    @Override
    public List<VersionedItem<?>> getRequiredMetamodelFragments() {
        return Collections.unmodifiableList(this.requiredMetamodelFragments);
    }

    @objid ("1a37df4b-7b85-4157-8830-56b94933e25f")
    @Override
    public List<VersionedItem<?>> getRequiredModelComponents() {
        return Collections.unmodifiableList(this.requiredModelComponents);
    }

    @objid ("9d139939-c9e9-49b9-a953-d0c34706b61b")
    @Override
    public List<ModelRef> getRoots() {
        return this.roots;
    }

    @objid ("a01e65fc-cc36-11e1-87f1-001ec947ccaf")
    @Override
    public Version getVersion() {
        return this.version;
    }

    @objid ("a01c03ad-cc36-11e1-87f1-001ec947ccaf")
     ModelComponentInfos() {
        
    }

    @objid ("740b57a5-cc3e-11e1-87f1-001ec947ccaf")
    void addFile(ExportedFile f) {
        this.exportedFiles.add(f);
    }

    @objid ("740b5796-cc3e-11e1-87f1-001ec947ccaf")
    void addModule(VersionedItem<?> module) {
        this.contributingModule.add(module);
    }

    @objid ("2a15388d-1c3d-4ea0-ae71-31902cb004b4")
    void addRequiredMetamodelFragment(VersionedItem<?> dep) {
        this.requiredMetamodelFragments.add(dep);
    }

    @objid ("a01e6609-cc36-11e1-87f1-001ec947ccaf")
    void addRequiredRamc(VersionedItem<?> dep) {
        this.requiredModelComponents.add(dep);
    }

    @objid ("fe7648ed-53c6-4d0c-b390-bb7df17cb36c")
    void addRoot(ModelRef mref) {
        this.roots.add(mref);
    }

    @objid ("a01e6606-cc36-11e1-87f1-001ec947ccaf")
    void setDescription(String description) {
        this.description = description;
    }

    @objid ("a01c03af-cc36-11e1-87f1-001ec947ccaf")
    void setName(String name) {
        this.name = name;
    }

    @objid ("a01c03b6-cc36-11e1-87f1-001ec947ccaf")
    void setVersion(Version version) {
        this.version = version;
    }

    @objid ("ec613ec5-475e-4778-95c2-00bdad1e2469")
    @Override
    public Version getModelioVersion() {
        return this.modelioVersion;
    }

    @objid ("dc39a1d5-14e8-4cd6-a7e7-8a1d50e666ab")
    public void setModelioVersion(Version modelioVersion) {
        this.modelioVersion = modelioVersion;
    }

    @objid ("0bb865cf-0c1c-43e6-b58f-a33466c69530")
    @Override
    public String getProvider() {
        return this.provider;
    }

    @objid ("e7dff75d-224d-4871-b027-5019342e47fd")
    public void setProvider(String provider) {
        this.provider = provider;
    }

}
