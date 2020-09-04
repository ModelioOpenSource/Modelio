/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link StructuredActivityNode} with << UML2SequenceNode >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("bdf50b00-893c-4015-b22e-78595230db6d")
public class UML2SequenceNode {
    @objid ("39dbf8c2-18ee-4f78-bee8-95afeb5d005d")
    public static final String STEREOTYPE_NAME = "UML2SequenceNode";

    /**
     * The underlying {@link StructuredActivityNode} represented by this proxy, never null.
     */
    @objid ("14f025ec-034f-486e-871f-0dd3e13f2ee7")
    protected final StructuredActivityNode elt;

    /**
     * Tells whether a {@link UML2SequenceNode proxy} can be instantiated from a {@link MObject} checking it is a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a40bcf2c-f215-4c3d-a1fe-832e637f55d4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StructuredActivityNode) && ((StructuredActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SequenceNode.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StructuredActivityNode} stereotyped << UML2SequenceNode >> then instantiate a {@link UML2SequenceNode} proxy.
     * 
     * @return a {@link UML2SequenceNode} proxy on the created {@link StructuredActivityNode}.
     */
    @objid ("8d2742e0-8200-4ecd-9ab5-0e2762a72a00")
    public static UML2SequenceNode create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StructuredActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SequenceNode.STEREOTYPE_NAME);
        return UML2SequenceNode.instantiate((StructuredActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2SequenceNode} proxy from a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StructuredActivityNode
     * @return a {@link UML2SequenceNode} proxy or <i>null</i>.
     */
    @objid ("b31449d9-5406-4023-8903-c58032364c96")
    public static UML2SequenceNode instantiate(StructuredActivityNode obj) {
        return UML2SequenceNode.canInstantiate(obj) ? new UML2SequenceNode(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2SequenceNode} proxy from a {@link StructuredActivityNode} stereotyped << UML2SequenceNode >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StructuredActivityNode}
     * @return a {@link UML2SequenceNode} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d7096cec-a74c-43ff-83d2-469c32b01c3e")
    public static UML2SequenceNode safeInstantiate(StructuredActivityNode obj) throws IllegalArgumentException {
        if (UML2SequenceNode.canInstantiate(obj))
        	return new UML2SequenceNode(obj);
        else
        	throw new IllegalArgumentException("UML2SequenceNode: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c5c22792-ffc6-413a-961f-cf47decfb3be")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2SequenceNode other = (UML2SequenceNode) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StructuredActivityNode}. 
     * @return the StructuredActivityNode represented by this proxy, never null.
     */
    @objid ("97b4fe18-bf8b-4728-9f2a-5204133d455c")
    public StructuredActivityNode getElement() {
        return this.elt;
    }

    @objid ("98e26863-06e4-4414-93bf-c434264c5947")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("50d479d7-d87f-43f8-bdcf-6b5e960c1966")
    protected UML2SequenceNode(StructuredActivityNode elt) {
        this.elt = elt;
    }

    @objid ("01ac84f0-0cf7-4434-b841-53d0f299b742")
    public static final class MdaTypes {
        @objid ("0de4f59e-8ac8-470d-87b6-90aa9eda6ad1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a75f89f9-1ea5-4eb5-9ac9-d7969c84b375")
        private static Stereotype MDAASSOCDEP;

        @objid ("c0c4b7e2-80fe-47e5-81e2-c9c429059a38")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5b88e02a-69d6-4659-a649-29f68f78e5f5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "fca54004-5d0d-11df-a996-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
