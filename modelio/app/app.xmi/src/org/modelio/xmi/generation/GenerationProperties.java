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
package org.modelio.xmi.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Stereotype;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.api.FormatExport;
import org.modelio.xmi.api.XMIExtension;
import org.modelio.xmi.model.objing.profile.PExportProfile;
import org.modelio.xmi.util.EcoreTypes;
import org.modelio.xmi.util.EcoreUMLTypes;
import org.modelio.xmi.util.ScopeChecker;
import org.modelio.xmi.util.XMIProperties;

@objid ("98f5a684-77ae-4b21-82bc-a3c04c5b7d2a")
public class GenerationProperties extends XMIProperties {
    @objid ("46e72215-7033-427c-9af6-1f48a79610d1")
    private static final String SYSML_PROFILE_UID = "00bc42a4-0000-1968-0000-000000000000";

    @objid ("112460c8-1a59-4588-82eb-e780052f8798")
    private List<Stereotype> defaultStereotyped = new ArrayList<>();

    @objid ("90ccd29d-375a-4112-8b92-31ee5c37b4ca")
    private Model ecoreModel = null;

    @objid ("0fc408a8-5c46-437f-a8d5-89bc5ca84960")
    private FormatExport exportVersion = FormatExport.UML211;

    @objid ("4b5680dd-212b-4728-832e-2b50bfee02c9")
    private List<org.eclipse.uml2.uml.Profile> exportedProfiles = new ArrayList<>();

    @objid ("57f39dbf-9634-4c3e-aac2-83556346fd0b")
    private XMIExtension fileExtension = XMIExtension.XMI;

    @objid ("81f8b91b-f30f-4a44-aac2-147c54f892d9")
    private boolean roundtripEnabled = false;

    @objid ("06757153-375d-4fcb-b0f5-7efb64461116")
    private org.eclipse.uml2.uml.Profile sysMLProfile = null;

    @objid ("7b32c52b-8dbe-46a7-9f19-de7e7da1b7da")
    private boolean sysmlApplied = false;

    @objid ("849ee5d4-c64a-4193-ace3-4df11c6db950")
    private static GenerationProperties INSTANCE = null;

    @objid ("ca721a7a-6d08-49e0-a77c-f258bb95fa3c")
    private EcoreTypes ecoreTypes = new EcoreTypes(FormatExport.UML211);

    @objid ("5ac05d25-7296-423e-9e24-a7cec1e85f73")
    private EcoreUMLTypes ecoreUMLTypes = null;

    @objid ("5609085f-c469-4da9-8141-1c5d6fadcd2c")
    private List<ModelTree> exportScopeElts;

    @objid ("a2230264-6301-47f1-857b-89ada3d553c3")
    private List<Project> libraryExported = new ArrayList<>();

    @objid ("d26bd79e-e60d-4e42-8514-e86ac873428a")
    private GenericMetamodelVisitor objingVisitor = null;

    @objid ("37affc3a-e487-435d-b113-1d957dd57670")
    private PartialCreationExportVisitor partialCreationExportVisitor = new PartialCreationExportVisitor();

    @objid ("9902dc1d-c2c6-46b7-908b-810be0e39eea")
    private MClass profileMClass;

    @objid ("7b124cbe-d3f6-4206-9f93-7122d1b43551")
    private ScopeChecker scopeChecker;

    @objid ("f6cf4db1-9777-4285-9b34-542c1b119ab5")
    private List<ModelElement> stereotypesExported = new ArrayList<>();

    @objid ("85d12117-bce0-4f81-b388-caad2b015663")
    private List<ModelElement> sysMLElementExported = new ArrayList<>();

    @objid ("041a5488-d325-4b32-9fc6-4d1fa4749e93")
    public void addExportedProfile(final org.eclipse.uml2.uml.Profile newProfile) {
        if (!(this.exportedProfiles.contains(newProfile))) {
            this.exportedProfiles.add(newProfile);
        }
        
    }

    @objid ("c442faee-ae80-4f4c-95f7-27d3e70ccfb0")
    public void addStereotypeExported(ModelElement element) {
        if (!this.stereotypesExported.contains(element)) {
            this.stereotypesExported.add(element);
        }
        
    }

    @objid ("9a23bb1d-1118-42e6-8d60-4abfe8002238")
    public void addSysMLExported(final ModelElement element) {
        if (!this.sysMLElementExported.contains(element)) {
            this.sysMLElementExported.add(element);
        }
        
    }

    @objid ("70ba6010-debf-4f44-b6dd-ada56337c4fc")
    public void cleanStereotypesExported() {
        this.stereotypesExported = new ArrayList<>();
    }

    @objid ("a8956671-c0c0-4ac2-a0aa-f8204b55a788")
    public void cleanSysMLElementExported() {
        this.sysMLElementExported = new ArrayList<>();
    }

    @objid ("416d95cc-499b-4566-a01f-f84d628c3f63")
    public List<Stereotype> getDefaultStereotyped() {
        return this.defaultStereotyped;
    }

    /**
     * This methods returns the list of Ecore classes corresponding to a given Modelio class name
     * @param ecoreClassName : the name of the Modelio class name
     * @return the list of the corresponding Ecore class names
     */
    @objid ("8dcb6901-9236-4210-be12-bd7770a59219")
    @Override
    public List<String> getClassNames(final String className) {
        //Class name without the metamodel prefix
        String realClassName = "";
        
        //Standard metamodel case
        if (className.startsWith("Standard.")){
            //remove Standard prefix
            realClassName = className.replace("Standard.", "");
        
            //return result
            if (this.umlClassTabConvertion.containsKey(realClassName))
                return this.umlClassTabConvertion.get(realClassName); 
        
        
        }else  if (className.startsWith("Infrastructure.")){           
            //remove Infrastructure prefix
            realClassName = className.replace("Infrastructure.", "");
        
            //return result
            if (this.infraClassTabConvertion.containsKey(realClassName))
                return this.infraClassTabConvertion.get(realClassName);        
        }
        return new ArrayList<>();
    }

    @objid ("0e191fb4-b93c-4c12-9096-616646c032fc")
    public Model getEcoreModel() {
        return this.ecoreModel;
    }

    @objid ("c6c21908-caa8-4ac2-bee5-2e8833b2e6f3")
    public EcoreTypes getEcoreTypes() {
        return this.ecoreTypes;
    }

    @objid ("03802a9a-0432-4bc6-86d3-d4785ab1a683")
    public EcoreUMLTypes getEcoreUMLTypes() {
        return this.ecoreUMLTypes;
    }

    @objid ("b7887236-0493-488d-af80-a4ffbc63b95e")
    public List<ModelTree> getExportScopeElts() {
        return this.exportScopeElts;
    }

    @objid ("9b140fe2-77e4-43cf-a616-3ae24cbaf2a2")
    public FormatExport getExportVersion() {
        return this.exportVersion;
    }

    @objid ("906eeaa2-092e-4609-89b5-1068214422e9")
    public List<org.eclipse.uml2.uml.Profile> getExportedProfiles() {
        return this.exportedProfiles;
    }

    @objid ("029a01a5-3b29-4fec-8896-632ac6d6b6fe")
    public XMIExtension getFileExtension() {
        return this.fileExtension;
    }

    @objid ("e3efa01d-21bd-4066-ad7c-fefd0fb27d1d")
    public static GenerationProperties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GenerationProperties();
        }
        return INSTANCE;
    }

    @objid ("811849de-5ee5-47e1-ba1a-465081add13f")
    public org.eclipse.uml2.uml.Element getMappedElement(MObject objingElt) {
        PartialExportMap partialCreationMap = PartialExportMap.getInstance();
        TotalExportMap totalCreationMap = TotalExportMap.getInstance();
        
        org.eclipse.uml2.uml.Element mappedElement = totalCreationMap.get(objingElt.getUuid().toString());
        
        if (mappedElement == null) {
            mappedElement = partialCreationMap.get(objingElt.getUuid().toString());
        }
        
        if (mappedElement == null) {
            // If the element is not in the scope, it will not be created
            // partially:
            if (this.scopeChecker.contains(objingElt)) {
                mappedElement = this.partialCreationExportVisitor
                        .createPartialEcoreElt(objingElt);
            }
        }
        return mappedElement;
    }

    @objid ("34fd8729-75d7-48e1-8c98-df97e5e2f82f")
    public GenericMetamodelVisitor getObjingVisitor() {
        return this.objingVisitor;
    }

    @objid ("91593fe8-1aff-4d65-a5f5-42c277d9ddd4")
    public PartialCreationExportVisitor getPartialCreationExportVisitor() {
        return this.partialCreationExportVisitor;
    }

    @objid ("72b9f461-de89-40ac-8f3d-bbf4091e1875")
    public ScopeChecker getScopeChecker() {
        return this.scopeChecker;
    }

    @objid ("8b2bad5b-4b50-45eb-8400-56f9fb25d839")
    public List<ModelElement> getStereotypesExported() {
        return this.stereotypesExported;
    }

    @objid ("0c63b847-850c-4dce-ad0b-271e3f1fd43a")
    public List<ModelElement> getSysMLExported() {
        return this.sysMLElementExported;
    }

    @objid ("a94c8c09-2e34-4242-ba56-721f46dca380")
    public org.eclipse.uml2.uml.Profile getSysMLProfile() {
        if (this.sysMLProfile == null){
        
            Profile obSysMLProfile = (Profile) this.mmServices.findById(this.profileMClass, SYSML_PROFILE_UID);
        
            if (obSysMLProfile != null){
                this.sysMLProfile = (org.eclipse.uml2.uml.Profile) TotalExportMap.getInstance().get(obSysMLProfile.getUuid().toString());
                //SysML export
                if (this.sysMLProfile == null) {
                    ProfileExportVisitorImpl profileVisitor = new ProfileExportVisitorImpl();
                    PExportProfile pprofile = new PExportProfile(obSysMLProfile);
                    profileVisitor.visit(pprofile);
                    this.sysMLProfile = (org.eclipse.uml2.uml.Profile) TotalExportMap.getInstance().get(obSysMLProfile.getUuid().toString());
        
                }     
            }
        }
        return this.sysMLProfile;
    }

    @objid ("e4d5d825-1157-45b8-adf0-8ec5bb65bd53")
    @Override
    public void initialize(final IMModelServices mmService, final MMetamodel metamodel, final IModelioNavigationService navigationService) {
        super.initialize(mmService, metamodel, navigationService);
        
        //Clear Export maps
        TotalExportMap.getInstance().clear();
        PartialExportMap.getInstance().clear();
        
        //Set primitive type correspondance
        this.ecoreUMLTypes = new EcoreUMLTypes();
        
        this.profileMClass = metamodel.getMClass(Profile.class);
        
        if (this.libraryExported != null){
            this.libraryExported.clear();
        }
        
    }

    @objid ("4443f7f1-8686-4f52-832f-ec1466f3fc0f")
    public void inverseProfiles(final org.eclipse.uml2.uml.Profile profileFirst, final org.eclipse.uml2.uml.Profile profileLast) {
        int indexFirst = this.exportedProfiles.indexOf(profileFirst);
        int indexLast = this.exportedProfiles.indexOf(profileLast);
        if (indexFirst > indexLast){
            this.exportedProfiles.remove(profileFirst);
            this.exportedProfiles.remove(profileLast);
            if (indexFirst < this.exportedProfiles.size() -1) {
                this.exportedProfiles.add(indexFirst,  profileFirst);
            } else {
                this.exportedProfiles.add(profileFirst);
            }
        
            if (indexLast < this.exportedProfiles.size() -1) {
                this.exportedProfiles.add(indexLast, profileLast);
            } else {
                this.exportedProfiles.add(profileLast);
            }
        
        }
        
    }

    @objid ("a11ab043-8f81-4989-81ca-0e6aa6ab19ba")
    public boolean isMappedElement(Element objingElt) {
        if (TotalExportMap.getInstance().get(objingElt.getUuid().toString()) != null) {
            return true;
        } else if (PartialExportMap.getInstance().get(objingElt.getUuid().toString()) != null) {
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * This method allows to know if the Modelio annotation are available
     * @return true if the imported XMI files contains Modelio annotations
     */
    @objid ("d051074a-a332-4d22-baeb-7e0ed37483ad")
    public boolean isRoundtripEnabled() {
        return this.roundtripEnabled;
    }

    @objid ("0034b388-8e8b-46b0-9c11-7f4913887d52")
    public boolean isSysMLApplied() {
        return this.sysmlApplied;
    }

    @objid ("c49e7e0b-7a44-410a-9d67-62a34c5deabf")
    public void setEcoreModel(org.eclipse.uml2.uml.Model ecoreModel) {
        this.ecoreModel = ecoreModel;
    }

    @objid ("935b07bc-dc78-45ce-9442-830228edb8dc")
    public void setExportScopeElts(List<ModelTree> rootElements) {
        this.exportScopeElts = rootElements;
    }

    @objid ("a7ecc9af-3462-4b2d-b045-1b7bcb7046fa")
    public void setFileExtension(XMIExtension ext) {
        this.fileExtension = ext;
    }

    @objid ("8154bb14-cf01-48ee-82cd-5700cd6b2fda")
    public void setObjingVisitor(GenericMetamodelVisitor objingVisitor) {
        this.objingVisitor = objingVisitor;
    }

    @objid ("01993cb6-c59e-48b3-8155-3c102da8d232")
    public void setRoundtripEnabled(boolean enableRoundtrip) {
        this.roundtripEnabled = enableRoundtrip;
    }

    @objid ("bd01716a-2452-4adb-9dab-8e625568f69e")
    public void setScopeChecker(ScopeChecker theScopeChecker) {
        this.scopeChecker = theScopeChecker;
    }

    @objid ("1510bb17-e352-42e6-b710-29eaa17efc3c")
    public void setSysMLApplied() {
        this.sysmlApplied = true;
    }

    @objid ("45a8150f-89cb-45ff-8dfa-cc7aebf4a5d8")
    public void setVersionExport(FormatExport version) {
        this.exportVersion = version;
        this.ecoreTypes = new EcoreTypes(version);
        
    }

    @objid ("9076ed4e-ee50-49ba-89ac-6b1541d0869b")
    public void supressProfile() {
        for (org.eclipse.uml2.uml.Profile current : this.exportedProfiles){
            if (this.ecoreModel.isProfileApplied(current)) {
                this.ecoreModel.unapplyProfile(current);
            }
        }
        this.exportedProfiles = new ArrayList<>();
        
    }

    @objid ("b5aa4ff9-0070-4f5a-8412-a391b83d71fc")
    public void supressSysMLProfile() {
        if (this.sysMLProfile != null){
            if (this.ecoreModel.isProfileApplied(this.sysMLProfile)){
                this.ecoreModel.unapplyProfile(this.sysMLProfile);
            }
            this.sysMLProfile = null;
        }
        
    }

    @objid ("f491dbd5-7dd6-444a-b2f9-999c3c23acc3")
    private  GenerationProperties() {
        
    }

    @objid ("9ad89e6f-0243-47b0-b8b2-268f9d3de1fb")
    @Override
    @SuppressWarnings("serial")
    protected void initUMLClassTabConvertion() {
        this.umlClassTabConvertion = new HashMap<>();
        this.umlClassTabConvertion.put("Abstraction",  new ArrayList<String>(){{add("Abstraction");}});
        this.umlClassTabConvertion.put("AcceptCallEventAction",  new ArrayList<String>(){{add("AcceptEventAction");}});
        this.umlClassTabConvertion.put("AcceptChangeEventAction",  new ArrayList<String>(){{add("AcceptEventAction");}});
        this.umlClassTabConvertion.put("AcceptSignalAction",  new ArrayList<String>(){{add("AcceptEventAction");}});
        this.umlClassTabConvertion.put("AcceptTimeEventAction",  new ArrayList<String>(){{add("AcceptEventAction");}});
        this.umlClassTabConvertion.put("Activity",  new ArrayList<String>(){{add("Activity");}});
        this.umlClassTabConvertion.put("ActivityEdge",  new ArrayList<String>(){{add("ActivityEdge");}});
        this.umlClassTabConvertion.put("ActivityFinalNode",  new ArrayList<String>(){{add("ActivityFinalNode");}});
        this.umlClassTabConvertion.put("ActivityNode",  new ArrayList<String>(){{add("ActivityNode");}});
        this.umlClassTabConvertion.put("ActivityParameterNode",  new ArrayList<String>(){{add("ActivityParameterNode");}});
        this.umlClassTabConvertion.put("ActivityPartition",  new ArrayList<String>(){{add("ActivityPartition");}});
        this.umlClassTabConvertion.put("Actor",  new ArrayList<String>(){{add("Actor");}});
        this.umlClassTabConvertion.put("Artifact",  new ArrayList<String>(){{add("Artifact");}});
        this.umlClassTabConvertion.put("Association",  new ArrayList<String>(){{add("Association"); add("AssociationClass");}});
        this.umlClassTabConvertion.put("AssociationEnd",  new ArrayList<String>(){{add("Property");}});
        this.umlClassTabConvertion.put("Attribute",  new ArrayList<String>(){{add("Property");}});
        this.umlClassTabConvertion.put("AttributeLink",  new ArrayList<String>(){{add("Slot");}});
        this.umlClassTabConvertion.put("Behavior",  new ArrayList<String>(){{add("Behavior");}});
        this.umlClassTabConvertion.put("BehaviorParameter",  new ArrayList<String>(){{add("Parameter");}});
        this.umlClassTabConvertion.put("BindableInstance",  new ArrayList<String>(){{add("Property");}});
        this.umlClassTabConvertion.put("Binding",  new ArrayList<String>(){{add("Dependency");}});
        this.umlClassTabConvertion.put("CallBehaviorAction",  new ArrayList<String>(){{add("CallBehaviorAction");}});
        this.umlClassTabConvertion.put("CallOperationAction",  new ArrayList<String>(){{add("CallOperationAction");}});
        this.umlClassTabConvertion.put("CentralBufferNode",  new ArrayList<String>(){{add("CentralBufferNode");}});
        this.umlClassTabConvertion.put("Class",  new ArrayList<String>(){{add("Class");}});
        this.umlClassTabConvertion.put("ClassAssociation",  new ArrayList<String>(){{add("AssociationClass");}});
        this.umlClassTabConvertion.put("Classifier",  new ArrayList<String>(){{add("Classifier");}});
        this.umlClassTabConvertion.put("Clause",  new ArrayList<String>(){{add("Clause");}});
        this.umlClassTabConvertion.put("Collaboration",  new ArrayList<String>(){{add("Collaboration");}});
        this.umlClassTabConvertion.put("CollaborationUse",  new ArrayList<String>(){{add("CollaborationUse");}});
        this.umlClassTabConvertion.put("Component",  new ArrayList<String>(){{add("Component");}});
        this.umlClassTabConvertion.put("ConditionalNode",  new ArrayList<String>(){{add("ConditionalNode");}});
        this.umlClassTabConvertion.put("Connector",  new ArrayList<String>(){{add("Connector");}});
        this.umlClassTabConvertion.put("ConnectorEnd",  new ArrayList<String>(){{add("ConnectorEnd");add("Slot");}});
        this.umlClassTabConvertion.put("Constraint",  new ArrayList<String>(){{add("Constraint");}});
        this.umlClassTabConvertion.put("ControlFlow",  new ArrayList<String>(){{add("ControlFlow");}});
        this.umlClassTabConvertion.put("DataFlow",  new ArrayList<String>(){{add("InformationFlow");}});
        this.umlClassTabConvertion.put("DataStoreNode",  new ArrayList<String>(){{add("DataStoreNode");}});
        this.umlClassTabConvertion.put("DataType",  new ArrayList<String>(){{add("DataType"); add("PrimitiveType");}});
        this.umlClassTabConvertion.put("DecisionMergeNode",  new ArrayList<String>(){{add("DecisionNode"); add("MergeNode");}});
        this.umlClassTabConvertion.put("DeepHistoryPseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.umlClassTabConvertion.put("ElementImport",  new ArrayList<String>(){{add("ElementImport");}});
        this.umlClassTabConvertion.put("ElementRealization",  new ArrayList<String>(){{add("Realization");}});
        this.umlClassTabConvertion.put("EntryPointPseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.umlClassTabConvertion.put("Enumeration",  new ArrayList<String>(){{add("Enumeration");}});
        this.umlClassTabConvertion.put("EnumerationLiteral",  new ArrayList<String>(){{add("EnumerationLiteral");}});
        this.umlClassTabConvertion.put("Event",  new ArrayList<String>(){{add("CallEvent"); add("SignalEvent");
        add("TimeEvent"); add("ChangeEvent"); add("CreationEvent"); add("DestructionEvent"); add("ExecutionEvent");}});
        this.umlClassTabConvertion.put("ExecutionOccurenceSpecification",  new ArrayList<String>(){{add("ExecutionOccurenceSpecification");}});
        this.umlClassTabConvertion.put("ExecutionSpecification",  new ArrayList<String>(){{add("ExecutionSpecification");}});
        this.umlClassTabConvertion.put("ExitPointPseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.umlClassTabConvertion.put("ExtensionPoint",  new ArrayList<String>(){{add("ExtensionPoint");}});
        this.umlClassTabConvertion.put("Feature",  new ArrayList<String>(){{add("Feature");}});
        this.umlClassTabConvertion.put("FinalState",  new ArrayList<String>(){{add("FinalState");}});
        this.umlClassTabConvertion.put("FlowFinalNode",  new ArrayList<String>(){{add("FlowFinalNode");}});
        this.umlClassTabConvertion.put("ForkJoinNode",  new ArrayList<String>(){{add("ForkNode"); add("JoinNode");}});
        this.umlClassTabConvertion.put("ForkPseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.umlClassTabConvertion.put("Generalization",  new ArrayList<String>(){{add("Generalization");}});
        this.umlClassTabConvertion.put("InformationFlow",  new ArrayList<String>(){{add("InformationFlow");}});
        this.umlClassTabConvertion.put("InformationItem",  new ArrayList<String>(){{add("InformationItem");}});
        this.umlClassTabConvertion.put("InitialNode",  new ArrayList<String>(){{add("InitialNode");}});
        this.umlClassTabConvertion.put("InitialPseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.umlClassTabConvertion.put("InputPin",  new ArrayList<String>(){{add("InputPin");add("ValuePin");
        add("ExpansionNode");add("ActionInputPin");}});
        this.umlClassTabConvertion.put("Instance",  new ArrayList<String>(){{add("InstanceSpecification"); add("Property");}});
        this.umlClassTabConvertion.put("InstanceNode",  new ArrayList<String>(){{add("CentralBufferNode");}});
        this.umlClassTabConvertion.put("Interaction",  new ArrayList<String>(){{add("Interaction");}});
        this.umlClassTabConvertion.put("Interface",  new ArrayList<String>(){{add("Interface");}});
        this.umlClassTabConvertion.put("InterfaceRealization",  new ArrayList<String>(){{add("InterfaceRealization");}});
        this.umlClassTabConvertion.put("InternalTransition",  new ArrayList<String>(){{add("Transition");}});
        this.umlClassTabConvertion.put("InterruptibleActivityRegion",  new ArrayList<String>(){{add("InterruptibleActivityRegion");}});
        this.umlClassTabConvertion.put("JoinPseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.umlClassTabConvertion.put("JunctionPseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.umlClassTabConvertion.put("Lifeline",  new ArrayList<String>(){{add("Lifeline");}});
        this.umlClassTabConvertion.put("Link",  new ArrayList<String>(){{add("InstanceSpecification");}});
        this.umlClassTabConvertion.put("LinkEnd",  new ArrayList<String>(){{add("Slot");}});
        this.umlClassTabConvertion.put("LoopNode",  new ArrayList<String>(){{add("LoopNode");}});
        this.umlClassTabConvertion.put("Manifestation",  new ArrayList<String>(){{add("Manifestation");}});
        this.umlClassTabConvertion.put("Node",  new ArrayList<String>(){{add("Node");}});
        this.umlClassTabConvertion.put("Note",  new ArrayList<String>(){{add("Comment");}});
        this.umlClassTabConvertion.put("ObjectFlow",  new ArrayList<String>(){{add("ObjectFlow");add("ExceptionHandler");}});
        this.umlClassTabConvertion.put("ObjectNode",  new ArrayList<String>(){{add("ObjectNode");}});
        this.umlClassTabConvertion.put("OpaqueAction",  new ArrayList<String>(){{add("OpaqueAction");add("ValueSpecificationAction");}});
        this.umlClassTabConvertion.put("Operation",  new ArrayList<String>(){{add("Operation");add("Reception");
        add("RedefinableTemplateSignature");add("TemplateSignature");}});
        this.umlClassTabConvertion.put("OutputPin",  new ArrayList<String>(){{add("OutputPin");}});
        this.umlClassTabConvertion.put("Package",  new ArrayList<String>(){{add("Package");}});
        this.umlClassTabConvertion.put("PackageImport",  new ArrayList<String>(){{add("PackageImport");}});
        this.umlClassTabConvertion.put("PackageMerge",  new ArrayList<String>(){{add("PackageMerge");}});
        this.umlClassTabConvertion.put("Parameter",  new ArrayList<String>(){{add("Parameter");add("ClassifierTemplateParameter");
        add("OperationTemplateParameter");}});
        this.umlClassTabConvertion.put("Pin",  new ArrayList<String>(){{add("Pin");}});
        this.umlClassTabConvertion.put("Port",  new ArrayList<String>(){{add("Port");}});
        this.umlClassTabConvertion.put("RaisedException",  new ArrayList<String>(){{add("Operation");}});
        this.umlClassTabConvertion.put("Region",  new ArrayList<String>(){{add("Region");}});
        this.umlClassTabConvertion.put("RequirementContainer",  new ArrayList<String>(){{add("Package");}});
        this.umlClassTabConvertion.put("SendSignalAction",  new ArrayList<String>(){{add("SendSignalAction");}});
        this.umlClassTabConvertion.put("Signal",  new ArrayList<String>(){{add("Signal");}});
        this.umlClassTabConvertion.put("State",  new ArrayList<String>(){{add("State");}});
        this.umlClassTabConvertion.put("StateMachine",  new ArrayList<String>(){{add("StateMachine");add("ProtocolStateMachine");}});
        this.umlClassTabConvertion.put("StructuredActivityNode",  new ArrayList<String>(){{add("StructuredActivityNode");add("ExpansionRegion");}});
        this.umlClassTabConvertion.put("Substitution",  new ArrayList<String>(){{add("Substitution");}});
        this.umlClassTabConvertion.put("TemplateBinding",  new ArrayList<String>(){{add("TemplateBinding");}});
        this.umlClassTabConvertion.put("TemplateParameter",  new ArrayList<String>(){{add("TemplateParameter");add("ConnectableElementTemplateParameter");}});
        this.umlClassTabConvertion.put("TemplateParameterSubstitution",  new ArrayList<String>(){{add("TemplateParameterSubstitution");}});
        this.umlClassTabConvertion.put("TerminatePseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.umlClassTabConvertion.put("Transition",  new ArrayList<String>(){{add("Transition");add("ProtocolTransition");}});
        this.umlClassTabConvertion.put("Usage",  new ArrayList<String>(){{add("Usage");}});
        this.umlClassTabConvertion.put("UseCase",  new ArrayList<String>(){{add("UseCase");}});
        this.umlClassTabConvertion.put("UseCaseDependency",  new ArrayList<String>(){{add("Extend");add("Include");}});
        
    }

    @objid ("09b74215-fde7-4796-a90e-5f662a48d6a0")
    @Override
    @SuppressWarnings ("serial")
    protected void initInfraClassTabConvertion() {
        this.infraClassTabConvertion = new HashMap<>();
        this.infraClassTabConvertion.put("Dependency",  new ArrayList<String>(){{add("Dependency"); add("Extend"); add("Include");}});
        this.infraClassTabConvertion.put("ModelElement",  new ArrayList<String>(){{add("NamedElement");}});
        this.infraClassTabConvertion.put("Stereotype",  new ArrayList<String>(){{add("Stereotype");}});
        
    }

}
