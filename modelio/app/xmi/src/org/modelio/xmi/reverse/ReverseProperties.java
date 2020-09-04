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

package org.modelio.xmi.reverse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Profile;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.EcoreUMLTypes;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.XMIProperties;

/**
 * This class handles the properties needed during an XMI import
 * @author ebrosse
 */
@objid ("d7222852-f092-473a-84f5-4de3008d8426")
public class ReverseProperties extends XMIProperties {
    @objid ("b522cf88-469f-42f9-97f5-193c91d438b9")
    private boolean roundtripEnabled = false;

    @objid ("a16cbd93-4d77-4707-a128-386aaf468046")
    private boolean timeDisplayerActivated = false;

    @objid ("d4965414-3cc6-4cdb-a329-c14ea76fd77f")
    private boolean rollback = false;

    @objid ("f08e70cf-7e1c-443e-a120-207612b37e9d")
    private List<String> appliedProfiles = new ArrayList<>();

    @objid ("d769c115-c3b1-48a2-95b3-fb2b4e95a672")
    private static final int marging = 15;

    @objid ("a25c15ca-6571-44ac-9a41-e08d51b87522")
    private static final int initLine = 40;

    @objid ("3f62e1b0-788c-411b-9a60-7de9cae8d3f2")
    private int currentLineNumber = 40;

    @objid ("689a7ae9-45cc-4d24-8879-5dc950befeda")
    private Set<org.eclipse.uml2.uml.Package> ecoreModel = null;

    @objid ("e25a4737-d716-4a21-ad57-659ae3d46858")
    private Profile ecoreProfile = null;

    @objid ("6e8c2e75-c3cd-4e9b-bbb9-d538605e08df")
    private static final String LOCAL_PROFILE = "LocalProfile";

    @objid ("457feea3-b041-4e62-992b-5bf109a11817")
    private Map<String, ArrayList<String>> classTabConvertion = null;

    @objid ("7beefb3c-d463-4ec5-8b7d-89feecdc450c")
    private EcoreUMLTypes ecoreUMLTypes = null;

    @objid ("cb60e208-a144-4cfd-92fe-4d7d7ee53948")
    private static ReverseProperties INSTANCE = null;

    @objid ("d02c70af-fc85-4875-bf92-549a09be81b4")
    private OwnedCompositionUml2Visitor ecoreVisitor = null;

    @objid ("17c713aa-7a43-4acc-a4c3-20f9a8a7c489")
    private PartialCreationImportVisitor partialCreationImportVisitor = null;

    @objid ("ddb86f20-d021-4fc3-adec-7ef8fe2f2c5c")
    private ModuleComponent profileRoot = null;

    @objid ("3fe1e1bb-e5da-45f5-bb57-d60d7f2aa7a2")
    private Package externalPackage = null;

//    @objid ("596c91ec-0d01-4f19-8f05-aba90e28c48b")
//    private Package umlRoot = null;
    @objid ("7151f17a-e796-4e5d-b9ff-626663c910e8")
    private ICoreSession session = null;

    @objid ("22aa6f77-813a-453c-8fb4-ef8a00d2e505")
    private org.modelio.metamodel.uml.infrastructure.Profile localProfile = null;

    @objid ("2444aff9-a11e-4168-9d45-fd622221fb46")
    private DataType unlimitedNatural = null;

    @objid ("526a8b54-c3a5-46b3-9fb4-b12324d2988f")
    private ReverseProperties() {
    }

    @objid ("effbc65a-d973-453b-92c0-8016d8a0eacf")
    public EcoreUMLTypes getEcoreUMLTypes() {
        return this.ecoreUMLTypes;
    }

    /**
     * Provide the instance of the class
     * @return the ReverseProperties instance
     */
    @objid ("afc8eee0-d9cf-4e10-abd0-3b4de48f584c")
    public static ReverseProperties getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ReverseProperties();
        return INSTANCE;
    }

    /**
     * provide the current Ecore Visitor
     * @return EcoreVisitor
     */
    @objid ("0faab48c-2db9-4b6e-b9ab-189b1d5179ad")
    public OwnedCompositionUml2Visitor getEcoreVisitor() {
        return this.ecoreVisitor;
    }

    /**
     * set the Ecore visitor for the current import
     * @param ecoreVisitor : the Ecore visitor
     */
    @objid ("8ed278dd-412e-403d-ac40-4a9f71542a81")
    public void setEcoreVisitor(OwnedCompositionUml2Visitor ecoreVisitor) {
        this.ecoreVisitor = ecoreVisitor;
    }

    /**
     * Provide the Ecore model resulting of the XMI export
     * @return Ecore model
     */
    @objid ("ca2d872a-4220-4f4a-829b-070aafed64be")
    public Set<org.eclipse.uml2.uml.Package> getEcoreModels() {
        return this.ecoreModel;
    }

    /**
     * This method allows to know if the Modelio annotation are available
     * @return true if the imported XMI files contains Modelio annotations
     */
    @objid ("e08b5bf6-c360-4dc5-9481-cc2adb3d6d9d")
    public boolean isRoundtripEnabled() {
        return this.roundtripEnabled;
    }

    /**
     * This method allows to know if the time displayer is activated
     * @return true if the time displayer is activated
     */
    @objid ("c2d48f9a-b3ef-4e4e-80b5-fa6b014f9095")
    public boolean isTimeDisplayerActivated() {
        return this.timeDisplayerActivated;
    }

    /**
     * This method allows to activated or not the time displayer for the current export
     * @param activateTimeDisplayer : if true is able the time displayer
     */
    @objid ("e0ac80db-2138-4ee0-87ff-bac7c456239a")
    public void setTimeDisplayerActivated(final boolean activateTimeDisplayer) {
        this.timeDisplayerActivated = activateTimeDisplayer;
    }

    /**
     * This methods initializes the properties of the import
     */
    @objid ("a0d160cc-4a43-4dd5-9448-d791c43784a4")
    @Override
    public void initialize(final IMModelServices mmService, final MMetamodel metamodel, final IModelioNavigationService navigationService) {
        super.initialize(mmService, metamodel, navigationService);
        
        //Clear Import maps
        TotalImportMap.getInstance().clear();
        PartialImportMap.getInstance().clear();
        
        //Set primitive type correspondance
        this.ecoreUMLTypes = new EcoreUMLTypes();
        
        //Initialize data
        this.appliedProfiles = new ArrayList<>();
        this.externalPackage = null;
        this.partialCreationImportVisitor = new PartialCreationImportVisitor();
          
        if (this.ecoreModel != null){
            this.ecoreModel.clear();
        }
        
        this.rollback = false;
    }

    /**
     * This method allows to add an Ecore model to the current reverses
     * @param model : the Ecore model to add
     */
    @objid ("e645384e-973b-4223-8301-f609e1d87d6d")
    public void addEcoreModel(org.eclipse.uml2.uml.Package model) {
        if (this.ecoreModel == null){
            this.ecoreModel = new HashSet<>();
        }
        
        this.ecoreModel.add(model);
    }

    /**
     * This method stores the availability of Modelio annotation in a specific Ecore model
     * @param importedPck : the given Ecore model
     */
    @objid ("b5876d5a-3148-4cc7-93e7-8d99b42f721d")
    public void setImportModelRoundtripMode(final org.eclipse.uml2.uml.Package importedPck) {
        this.roundtripEnabled = ObjingEAnnotation.isRoundTrip(importedPck);
    }

    /**
     * This method returns the import result of a given Ecore element
     * @param ecoreElt : the imported Ecore element
     * @return the result of the import
     */
    @objid ("e2f5196d-e41e-4e15-9fbd-dd92624bf1fb")
    public Object getMappedElement(org.eclipse.uml2.uml.Element ecoreElt) {
        PartialImportMap partialCreationMap = PartialImportMap.getInstance();
        TotalImportMap totalCreationMap = TotalImportMap.getInstance();
        
        Object mappedElement = totalCreationMap.get(ecoreElt);
        
        if (mappedElement == null)
            mappedElement = partialCreationMap.get(ecoreElt);
        
        if (mappedElement == null) {
            mappedElement = this.partialCreationImportVisitor
                    .createPartialObjingElt(ecoreElt);
        }
        return mappedElement;
    }

    /**
     * This method tests if a given Ecore element has been already mapped
     * @param ecoreElt : the tested element
     * @return true if the tested element has been mapped
     */
    @objid ("3a770e32-6569-420c-ba3e-fccbf7a17d15")
    public boolean isMappedElement(org.eclipse.uml2.uml.Element ecoreElt) {
        if (TotalImportMap.getInstance().get(ecoreElt) != null)
            return true;
        else if (PartialImportMap.getInstance().get(ecoreElt) != null)
            return true;
        else
            return false;
    }

    /**
     * This method sets the profile root
     * @param root : the profile root
     */
    @objid ("5c6116be-d81b-4ce5-aa50-e0ad81feb48f")
    public void setProfileRoot(ModuleComponent root) {
        this.profileRoot = root;
    }

    /**
     * This method returns the profile root of the current export
     * @return the profile root
     */
    @objid ("7ba3ac42-e251-4d3c-9f88-db632e9db5ba")
    public ModuleComponent getProfileRoot() {
        if (this.profileRoot != null)
            return this.profileRoot;
        
        if(!this.rootElements.isEmpty()){
            ModelElement root = this.rootElements.get(0);
            for (MObject module : GProject.getProject(root).getFragment(root).getRoots()){
                if ((module instanceof ModuleComponent) && (module.getName().equals("LocalModule"))){
                    return (ModuleComponent) module;
                }
            }
        }
        return null;
    }

    /**
     * This method sets the imported Ecore profile
     * @param profile : the imported profile
     */
    @objid ("8d8ecf91-68de-49cd-b2ba-c51e692c3fc0")
    public void setEcoreProfile(final org.eclipse.uml2.uml.Profile profile) {
        this.ecoreProfile = profile;
    }

    /**
     * This method returns the imported Ecore profile
     * @return the imported Ecore profile
     */
    @objid ("987d9e80-701f-4b05-9ff4-57a27bc1434c")
    public org.eclipse.uml2.uml.Profile getEcoreProfile() {
        return this.ecoreProfile;
    }

    /**
     * This method allows to know if the import would be rollbacked
     * @return true if the import must be rollbacked
     */
    @objid ("c164e4e8-a55e-4329-8366-83c71d13ce7b")
    public boolean isRollback() {
        return this.rollback;
    }

    /**
     * This method allows to specify if the import transaction must be rollbacked
     * @param rollback : need of rollbacking the current transaction
     */
    @objid ("855d6f15-7bbd-4207-a1f4-13b02c311063")
    public void setRollback(boolean rollback) {
        this.rollback = rollback;
    }

    /**
     * This method clean the properties of the current import
     */
    @objid ("60b2a3eb-2232-4e7c-89a6-8d5c2b44ea98")
    public void clean() {
        this.ecoreModel.clear();
        this.externalPackage = null;
        this.unlimitedNatural = null;
        this.appliedProfiles = null;
    }

    /**
     * This methods allows to add a profile to the list of applied profiles
     * @param appliedProfile : the profile to add
     */
    @objid ("b2d63ec7-36a7-4150-95d3-aab6da3aafc9")
    public void addAppliedProfile(final String appliedProfile) {
        if (!this.appliedProfiles.contains(appliedProfile))
            this.appliedProfiles.add(appliedProfile);
    }

    /**
     * This method returns the 'external' Package used in order to store additional elements
     * @return the 'external' Package
     */
    @objid ("eb3df0ab-642c-495f-893e-0d8d67582555")
    public Package getExternalPackage() {
        if (this.externalPackage == null){
            this.externalPackage = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createPackage();
            this.externalPackage.setName(Xmi.I18N.getString("Ui.ExternalPackage.Name"));
            ModelElement root = this.rootElements.get(0);
            for (MObject modelRoot : GProject.getProject(root).getFragment(root).getRoots()){
                if (modelRoot instanceof Project) {
                    EList<Package> model = ((Project) modelRoot).getModel();
                    if (model.size() > 0) {
                        this.externalPackage.setOwner(model.get(0));
                    }
                }
            }
        
        }
        return this.externalPackage;
    }

    /**
     * This method returns the list of imported profiles
     * @return imported profiles
     */
    @objid ("b4c46a70-26db-4c43-a112-a5e64c0b6dc8")
    public List<String> getAppliedProfiles() {
        return this.appliedProfiles;
    }

    /**
     * This method adds a given profile to the list of imported profile
     * @param importedProfiles : the profiles to add
     */
    @objid ("40cc13f4-959a-400a-9dcd-8f359e4291eb")
    public void addAppliedProfiles(final ArrayList<String> importedProfiles) {
        this.appliedProfiles.addAll(importedProfiles);
    }

    @objid ("4236fdb5-440e-42c8-94c2-a965990cc4a6")
    @SuppressWarnings("serial")
    private void initClassTabConvertion() {
        this.classTabConvertion = new HashMap<>();
        this.classTabConvertion.put("Abstraction",  new ArrayList<String>(){{add("Dependency");}});
        this.classTabConvertion.put("AcceptCallEventAction",  new ArrayList<String>(){{add("AcceptCallEventAction");}});
        this.classTabConvertion.put("AcceptEventAction",  new ArrayList<String>(){{add("AcceptCallEventAction");
        add("AcceptSignalAction");    add("AcceptChangeEventAction");add("AcceptTimeEventAction");}});
        this.classTabConvertion.put("Action",  new ArrayList<String>(){{add("ActivityAction");}});
        this.classTabConvertion.put("ActionInputPin",  new ArrayList<String>(){{add("InputPin");}});
        this.classTabConvertion.put("Activity",  new ArrayList<String>(){{add("Activity");}});
        this.classTabConvertion.put("ActivityEdge",  new ArrayList<String>(){{add("ActivityEdge");}});
        this.classTabConvertion.put("ActivityFinalNode",  new ArrayList<String>(){{add("ActivityFinalNode");}});
        this.classTabConvertion.put("ActivityParameterNode",  new ArrayList<String>(){{add("ActivityParameterNode");}});
        this.classTabConvertion.put("ActivityPartition",  new ArrayList<String>(){{add("ActivityPartition");}});
        this.classTabConvertion.put("Actor",  new ArrayList<String>(){{add("Actor");}});
        this.classTabConvertion.put("AddStructuralFeature",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("AddVariableValueAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("AnyReceiveEvent",  new ArrayList<String>(){{add("Event");}});
        this.classTabConvertion.put("Artifact",  new ArrayList<String>(){{add("Artifact");}});
        this.classTabConvertion.put("Association",  new ArrayList<String>(){{add("Association");}});
        this.classTabConvertion.put("AssociationClass",  new ArrayList<String>(){{add("Class");}});
        this.classTabConvertion.put("Behavior",  new ArrayList<String>(){{add("Behavior");}});
        this.classTabConvertion.put("BehaviorFeature",  new ArrayList<String>(){{add("Operation");}});
        this.classTabConvertion.put("BroadcastSignalAction",  new ArrayList<String>(){{add("SendSignalAction");}});
        this.classTabConvertion.put("CallBehaviorAction",  new ArrayList<String>(){{add("CallBehaviorAction");}});
        this.classTabConvertion.put("CallEvent",  new ArrayList<String>(){{add("Event");}});
        this.classTabConvertion.put("CallOperationAction",  new ArrayList<String>(){{add("CallOperationAction");}});
        this.classTabConvertion.put("CentralBufferNode",  new ArrayList<String>(){{add("CentralBufferNode");}});
        this.classTabConvertion.put("ChangeEvent",  new ArrayList<String>(){{add("Event");}});
        this.classTabConvertion.put("Class",  new ArrayList<String>(){{add("Class");}});
        this.classTabConvertion.put("Classifier",  new ArrayList<String>(){{add("Classifier");
        add("Association"); add("Collaboration");}});
        this.classTabConvertion.put("ClassifierTemplateParameter",  new ArrayList<String>(){{add("TemplateParameter");}});
        this.classTabConvertion.put("Clause",  new ArrayList<String>(){{add("Clause");}});
        this.classTabConvertion.put("ClearAssociationAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ClearStructuralFeatureAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ClearVariableAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("Collaboration",  new ArrayList<String>(){{add("Collaboration");}});
        this.classTabConvertion.put("CollaborationUse",  new ArrayList<String>(){{add("CollaborationUse");}});
        this.classTabConvertion.put("Comment",  new ArrayList<String>(){{add("Note");}});
        this.classTabConvertion.put("CommunicationPath",  new ArrayList<String>(){{add("Association");}});
        this.classTabConvertion.put("Component",  new ArrayList<String>(){{add("Component");}});
        this.classTabConvertion.put("ConditionalNode",  new ArrayList<String>(){{add("ConditionalNode");}});
        this.classTabConvertion.put("ConnectableElement",  new ArrayList<String>(){{add("Parameter");
        add("BehaviorParameter"); add("AssociationEnd"); add("BindableInstance"); add("Attribute");}});
        this.classTabConvertion.put("Connector",  new ArrayList<String>(){{add("Connector");}});
        this.classTabConvertion.put("ConnectorEnd",  new ArrayList<String>(){{add("ConnectorEnd");}});
        this.classTabConvertion.put("Constraint",  new ArrayList<String>(){{add("Class");}});
        this.classTabConvertion.put("ControlFlow",  new ArrayList<String>(){{add("ControlFlow");}});
        this.classTabConvertion.put("CreateLinkAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("CreateLinkObject",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("CreateObjectAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("CreationEvent",  new ArrayList<String>(){{add("Event");}});
        this.classTabConvertion.put("DataStoreNode",  new ArrayList<String>(){{add("DataStoreNode");}});
        this.classTabConvertion.put("DataType",  new ArrayList<String>(){{add("DataType");}});
        this.classTabConvertion.put("DecisionNode",  new ArrayList<String>(){{add("DecisionMergeNode");}});
        this.classTabConvertion.put("Dependency",  new ArrayList<String>(){{add("Dependency");}});
        this.classTabConvertion.put("Deployment",  new ArrayList<String>(){{add("Dependency");}});
        this.classTabConvertion.put("DeploymentSpecification",  new ArrayList<String>(){{add("Artifact");}});
        this.classTabConvertion.put("DestroyLinkAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("DestroyObjectAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("DestructionEvent",  new ArrayList<String>(){{add("Event");}});
        this.classTabConvertion.put("Device",  new ArrayList<String>(){{add("Node");}});
        this. classTabConvertion.put("Element",  new ArrayList<String>(){{add("Element");}});
        this.classTabConvertion.put("ElementImport",  new ArrayList<String>(){{add("ElementImport");}});
        this.classTabConvertion.put("Enumeration",  new ArrayList<String>(){{add("Enumeration");}});
        this.classTabConvertion.put("EnumerationLiteral",  new ArrayList<String>(){{add("EnumerationLiteral");}});
        this.classTabConvertion.put("Event",  new ArrayList<String>(){{add("Event");}});
        this.classTabConvertion.put("ExceptionHandler",  new ArrayList<String>(){{add("ObjectFlow");}});
        this.classTabConvertion.put("ExecutionEnvironment",  new ArrayList<String>(){{add("Node");}});
        this.classTabConvertion.put("ExecutionEvent",  new ArrayList<String>(){{add("Event");}});
        this.classTabConvertion.put("ExpansionNode",  new ArrayList<String>(){{add("Pin");}});
        this.classTabConvertion.put("ExpansionRegion",  new ArrayList<String>(){{add("StructuredActivityNode");}});
        this.classTabConvertion.put("Extend",  new ArrayList<String>(){{add("UseCaseDependency");}});
        this.classTabConvertion.put("ExtensionPoint",  new ArrayList<String>(){{add("ExtensionPoint");}});
        this.classTabConvertion.put("Feature",  new ArrayList<String>(){{add("Feature");}});
        this.classTabConvertion.put("FinalState",  new ArrayList<String>(){{add("FinalState");}});
        this.classTabConvertion.put("FlowFinalNode",  new ArrayList<String>(){{add("FlowFinalNode");}});
        this.classTabConvertion.put("ForkNode",  new ArrayList<String>(){{add("ForkJoinNode");}});
        this.classTabConvertion.put("Gate",  new ArrayList<String>(){{add("Gate");}});
        this.classTabConvertion.put("Generalization",  new ArrayList<String>(){{add("Generalization");}});
        this.classTabConvertion.put("Include",  new ArrayList<String>(){{add("UseCaseDependency");}});
        this.classTabConvertion.put("InformationFlow",  new ArrayList<String>(){{add("InformationFlow");}});
        this.classTabConvertion.put("InformationItem",  new ArrayList<String>(){{add("InformationItem");}});
        this.classTabConvertion.put("InitialNode",  new ArrayList<String>(){{add("InitialNode");}});
        this.classTabConvertion.put("InputPin",  new ArrayList<String>(){{add("InputPin");}});
        this. classTabConvertion.put("InstanceSpecification",  new ArrayList<String>(){{add("Link");
        add("Instance");add("EnumerationLiteral");}});
        this.classTabConvertion.put("Interaction",  new ArrayList<String>(){{add("Interaction");}});
        this.classTabConvertion.put("Interface",  new ArrayList<String>(){{add("Interface");}});
        this.classTabConvertion.put("InterfaceRealization",  new ArrayList<String>(){{add("InterfaceRealization");}});
        this.classTabConvertion.put("InterruptibleActivityRegion",  new ArrayList<String>(){{add("InterruptibleActivityRegion");}});
        this.classTabConvertion.put("InvocationAction",  new ArrayList<String>(){{add("CallAction");
        add("SendSignalAction"); add("OpaqueAction");}});
        this.classTabConvertion.put("JoinNode",  new ArrayList<String>(){{add("ForkJoinNode");}});
        this.classTabConvertion.put("Lifeline",  new ArrayList<String>(){{add("Lifeline");}});
        this.classTabConvertion.put("LoopNode",  new ArrayList<String>(){{add("LoopNode");}});
        this.classTabConvertion.put("Manifestation",  new ArrayList<String>(){{add("Manifestation");}});
        this.classTabConvertion.put("MergeNode",  new ArrayList<String>(){{add("DecisionMergeNode");}});
        this.classTabConvertion.put("Message",  new ArrayList<String>(){{add("Message");}});
        this.classTabConvertion.put("MessageOccurrenceSpecification",  new ArrayList<String>(){{add("MessageOccurrenceSpecification");}});
        this.classTabConvertion.put("Model",  new ArrayList<String>(){{add("Package");}});
        this.classTabConvertion.put("ModelElement",  new ArrayList<String>(){{add("NamedElement");}});
        this.classTabConvertion.put("Node",  new ArrayList<String>(){{add("Node");}});
        this.classTabConvertion.put("ObjectFlow",  new ArrayList<String>(){{add("ObjectFlow");}});
        this.classTabConvertion.put("ObjectNode",  new ArrayList<String>(){{add("ObjectNode");}});
        this.classTabConvertion.put("OccurenceSpecification",  new ArrayList<String>(){{add("OccurenceSpecification");}});
        this.classTabConvertion.put("OpaqueAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("OpaqueBehavior",  new ArrayList<String>(){{add("Signal");}});
        this.classTabConvertion.put("Operation",  new ArrayList<String>(){{add("Operation");}});
        this.classTabConvertion.put("OperationTemplateParameter",  new ArrayList<String>(){{add("Parameter");}});
        this.classTabConvertion.put("OutputPin",  new ArrayList<String>(){{add("OutputPin");}});
        this.classTabConvertion.put("Package",  new ArrayList<String>(){{add("Package");}});
        this.classTabConvertion.put("PackageImport",  new ArrayList<String>(){{add("PackageImport");}});
        this.classTabConvertion.put("PackageMerge",  new ArrayList<String>(){{add("PackageMerge");}});
        this.classTabConvertion.put("Parameter",  new ArrayList<String>(){{add("Parameter");}});
        this.classTabConvertion.put("Pin",  new ArrayList<String>(){{add("Pin");}});
        this.classTabConvertion.put("Port",  new ArrayList<String>(){{add("Port");}});
        this.classTabConvertion.put("PrimitiveType",  new ArrayList<String>(){{add("DataType");}});
        this.classTabConvertion.put("Profile",  new ArrayList<String>(){{add("Profile");}});
        this.classTabConvertion.put("Property",  new ArrayList<String>(){{add("AssociationEnd");
        add("BindableInstance"); add("Attribute");}});
        this.classTabConvertion.put("ProtocolConformance",  new ArrayList<String>(){{add("Dependency");}});
        this.classTabConvertion.put("ProtocolStateMachine",  new ArrayList<String>(){{add("StateMachine");}});
        this.classTabConvertion.put("ProtocolTransition",  new ArrayList<String>(){{add("Transition");add("InternalTransition");}});
        this.classTabConvertion.put("PseudoState",  new ArrayList<String>(){{add("PseudoState");}});
        this.classTabConvertion.put("RaiseExceptionAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReadExtentAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReadIsClassifierAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReadLinkAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReadLinkObjectEndAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReadLinkObjectEndQualifierAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReadSelfAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReadStructuralFeatureAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReadVariableAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("Realization",  new ArrayList<String>(){{add("Realization");}});
        this.classTabConvertion.put("Reception",  new ArrayList<String>(){{add("Operation");}});
        this.classTabConvertion.put("ReclassifyObjectAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("RedefinableTemplateSignature",  new ArrayList<String>(){{add("Operation");}});
        this.classTabConvertion.put("ReduceAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("Region",  new ArrayList<String>(){{add("Region");}});
        this.classTabConvertion.put("RemoveStructuralFeatureValueAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("RemoveVariableValueAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("ReplyAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("SendObjectAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("SendSignalAction",  new ArrayList<String>(){{add("SendSignalAction");}});
        this.classTabConvertion.put("SequenceNode",  new ArrayList<String>(){{add("StructuredActivityNode");}});
        this.classTabConvertion.put("Signal",  new ArrayList<String>(){{add("Signal");}});
        this.classTabConvertion.put("Slot",  new ArrayList<String>(){{add("Port");
        add("BindableInstance");add("ConnectorEnd");add("LinkEnd");}});
        this.classTabConvertion.put("StartClassifierBehaviorAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("State",  new ArrayList<String>(){{add("State");}});
        this.classTabConvertion.put("StateInvariant",  new ArrayList<String>(){{add("StateInvariant");}});
        this.classTabConvertion.put("StateMachine",  new ArrayList<String>(){{add("StateMachine");}});
        this.classTabConvertion.put("Stereotype",  new ArrayList<String>(){{add("Stereotype");}});
        this.classTabConvertion.put("StructuredActivityNode",  new ArrayList<String>(){{add("StructuredActivityNode");}});
        this.classTabConvertion.put("StructuredClassifier",  new ArrayList<String>(){{add("Collaboration");
        add("Class");add("Behavior");add("Node");}});
        this.classTabConvertion.put("Substitution",  new ArrayList<String>(){{add("Substitution");}});
        this.classTabConvertion.put("TemplateBinding",  new ArrayList<String>(){{add("TemplateBinding");}});
        this.classTabConvertion.put("TemplateParameter",  new ArrayList<String>(){{add("TemplateParameter");}});
        this.classTabConvertion.put("TemplateParameterSubstitution",  new ArrayList<String>(){{add("TemplateParameterSubstitution");}});
        this.classTabConvertion.put("TemplateSignature",  new ArrayList<String>(){{add("Operation");}});
        this.classTabConvertion.put("TestIdentityAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("TimeEvent",  new ArrayList<String>(){{add("Event");}});
        this.classTabConvertion.put("Transition",  new ArrayList<String>(){{add("Transition");
        add("InternalTransition");}});
        this.classTabConvertion.put("UnmarshallAction",  new ArrayList<String>(){{add("OpaqueAction");}});
        this.classTabConvertion.put("Usage",  new ArrayList<String>(){{add("Usage");}});
        this.classTabConvertion.put("UseCase",  new ArrayList<String>(){{add("UseCase");}});
        this.classTabConvertion.put("ValuePin",  new ArrayList<String>(){{add("InputPin");}});
        this.classTabConvertion.put("ValueSpecificationAction",  new ArrayList<String>(){{add("OpaqueAction");}});
    }

    /**
     * This methods returns the list of Modelio classes corresponding to a given Ecore class
     * @param ecoreClassName : the name of the Ecore class
     * @return the list of the corresponding Modelio classes
     */
    @objid ("d845209b-a835-4016-ba71-44e07493f221")
    public List<String> getObjClassNames(final String ecoreClassName) {
        if (this.classTabConvertion == null){
            initClassTabConvertion();
        }
        
        List<String> result = this.classTabConvertion.get(ecoreClassName);
        
        if (result == null){
            return new ArrayList<>();
        }else{
            return result;
        }
    }

    @objid ("0671fc28-6159-4b0d-9430-9f18ce71b073")
    public void resetLineNumber() {
        this.currentLineNumber = initLine;
    }

    @objid ("2e549b88-5117-4489-80a2-6f43f071571d")
    public int getCurrentLineNumber() {
        this.currentLineNumber += marging ;
        return this.currentLineNumber;
    }

    @objid ("b5ad0ff0-dd97-4498-a118-98ea101d35a7")
    public void setCoreSession(ICoreSession session) {
        this.session = session;
    }

    @objid ("3af33d18-7b82-4bc2-81f6-b24bd884766f")
    public ICoreSession getCoreSession() {
        return this.session;
    }

    @objid ("23d99dd6-b9cf-4530-af53-d7388613fefa")
    public org.modelio.metamodel.uml.infrastructure.Profile getLocalProfile() {
        if (this.localProfile == null){
            ModelElement root = this.rootElements.get(0);
            for (MObject module :  GProject.getProject(root).getFragment(root).getRoots()){
                if ((module instanceof ModuleComponent) && (module.getName().equals("LocalModule"))){
        
                    //LocalProfile exists
                    for(org.modelio.metamodel.uml.infrastructure.Profile profile : ((ModuleComponent) module).getOwnedProfile()){
                        if (profile.getName().equals(LOCAL_PROFILE)){
                            this.localProfile = profile;
                            return profile;
                        }
                    }
        
                    //LocalProfile does not exist
                    this.localProfile = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createProfile();
                    this.localProfile.setName(LOCAL_PROFILE);
                    this.localProfile.setOwnerModule((ModuleComponent) module); 
                }
            }
            
        }
        return this.localProfile;
    }

    @objid ("11626ef9-9c10-4092-9f30-72a9a2b0994c")
    public DataType getUnlimitedNatural() {
        if (this.unlimitedNatural == null){
            this.unlimitedNatural = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class)
                    .createDataType(this.unlimitedNaturalName, getExternalPackage()); 
        }
        return this.unlimitedNatural;
    }

}
