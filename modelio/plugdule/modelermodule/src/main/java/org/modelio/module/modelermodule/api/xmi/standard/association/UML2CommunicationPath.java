/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.association;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} with << UML2CommunicationPath >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("81678c9e-2f43-40bc-ad65-f1a578bb0dee")
public class UML2CommunicationPath {
    @objid ("44e09554-5d84-4c3f-9562-ed679170f8cf")
    public static final String STEREOTYPE_NAME = "UML2CommunicationPath";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("d1e86484-4490-4a59-bb5b-7fdfdf7679e3")
    protected final Association elt;

    /**
     * Tells whether a {@link UML2CommunicationPath proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << UML2CommunicationPath >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6b7f726f-f2b4-47c3-a8f0-224901a64d3b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CommunicationPath.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << UML2CommunicationPath >> then instantiate a {@link UML2CommunicationPath} proxy.
     * 
     * @return a {@link UML2CommunicationPath} proxy on the created {@link Association}.
     */
    @objid ("0f9beecd-d179-4250-851e-8b1883e4ef4f")
    public static UML2CommunicationPath create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CommunicationPath.STEREOTYPE_NAME);
        return UML2CommunicationPath.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link UML2CommunicationPath} proxy from a {@link Association} stereotyped << UML2CommunicationPath >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link UML2CommunicationPath} proxy or <i>null</i>.
     */
    @objid ("805c39e0-2a1d-4fb8-9133-7dd09e4470da")
    public static UML2CommunicationPath instantiate(Association obj) {
        return UML2CommunicationPath.canInstantiate(obj) ? new UML2CommunicationPath(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CommunicationPath} proxy from a {@link Association} stereotyped << UML2CommunicationPath >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link UML2CommunicationPath} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("87a21789-28b5-4609-9be3-e9847e8baf98")
    public static UML2CommunicationPath safeInstantiate(Association obj) throws IllegalArgumentException {
        if (UML2CommunicationPath.canInstantiate(obj))
        	return new UML2CommunicationPath(obj);
        else
        	throw new IllegalArgumentException("UML2CommunicationPath: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0c899761-9fba-4263-9619-0b121c372644")
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
        UML2CommunicationPath other = (UML2CommunicationPath) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("17d53b70-65a0-4979-b339-031ed39e78a6")
    public Association getElement() {
        return this.elt;
    }

    @objid ("f89b6187-244a-43bc-aa40-822125f1e866")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("691f8178-e506-4339-bae7-01b3a9397cc7")
    protected UML2CommunicationPath(Association elt) {
        this.elt = elt;
    }

    @objid ("d8b8c58e-ba0a-4cce-937a-c14207a1cafc")
    public static final class MdaTypes {
        @objid ("6e0c1f8f-dba3-4071-82ba-120e43ba907c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ecc847d8-0886-4a28-a433-62241d972fe9")
        private static Stereotype MDAASSOCDEP;

        @objid ("839bfb2d-49cb-4afc-875e-e3f475b38b68")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c49c0627-d6da-4f35-98b3-83e617eeeff5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8ed6276e-5821-11df-be59-001302895b2b");
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
