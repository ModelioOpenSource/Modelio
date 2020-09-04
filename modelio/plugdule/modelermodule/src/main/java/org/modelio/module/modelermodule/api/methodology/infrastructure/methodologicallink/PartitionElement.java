/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << PartitionElement >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Acteurs, rôles : drag & drop dans les lanes & pool permettent de les associer et faire apparaitre. Les icones (ArchiMate) doivent s’afficher par défaut.</i></p>
 */
@objid ("de449f4b-02f1-4a89-aee5-f8ce3007641b")
public class PartitionElement {
    @objid ("e2a2f0de-1066-4143-bb61-531f8df2a05c")
    public static final String STEREOTYPE_NAME = "PartitionElement";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("428c3cc0-5b40-4e05-8e51-7474cf09398a")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link PartitionElement proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << PartitionElement >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e93e794e-6ced-4e67-b33c-e786f771393f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PartitionElement.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << PartitionElement >> then instantiate a {@link PartitionElement} proxy.
     * 
     * @return a {@link PartitionElement} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("6656c6e5-d8d0-4dd0-a7a3-1426eb37ec3f")
    public static PartitionElement create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PartitionElement.STEREOTYPE_NAME);
        return PartitionElement.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link PartitionElement} proxy from a {@link MethodologicalLink} stereotyped << PartitionElement >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link PartitionElement} proxy or <i>null</i>.
     */
    @objid ("c5297988-d247-4497-bf71-ab04021a40e5")
    public static PartitionElement instantiate(MethodologicalLink obj) {
        return PartitionElement.canInstantiate(obj) ? new PartitionElement(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PartitionElement} proxy from a {@link MethodologicalLink} stereotyped << PartitionElement >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link PartitionElement} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6c43078a-1555-42c7-bb8a-1a52c6a100ce")
    public static PartitionElement safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (PartitionElement.canInstantiate(obj))
        	return new PartitionElement(obj);
        else
        	throw new IllegalArgumentException("PartitionElement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("91331fb1-fdcc-401b-9b23-a8272f6184f5")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("67e8e257-20b2-4f61-9cba-f089e9bc439f")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("24ecc3a3-c3d2-4030-a90c-3556d6902514")
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
        PartitionElement other = (PartitionElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("f6763019-79f0-4aeb-9a5a-f41666ab52d1")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("9f441fc1-7fe4-4418-81d2-b2ba2de44f02")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a20a9f88-2427-498d-8b55-c66aaa38a85b")
    protected PartitionElement(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("5e6cefd6-55cf-4f85-b0bc-77215412fc10")
    public static final class MdaTypes {
        @objid ("3d880506-77e7-45f2-92dd-efaa799855d6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2ca68eed-9915-4b83-b4df-df455167c67b")
        private static Stereotype MDAASSOCDEP;

        @objid ("3c91ce5a-ffa6-47db-ad0d-e70a8f9af96f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e006b168-f04d-4973-96e8-9d1b738c3296")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5de33d2a-ed28-439c-aa09-d11bf1a6d878");
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
