/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
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
 * Proxy class to handle a {@link Actor} with << primary >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("29339174-3c68-4de4-ad0a-02e0b97a6a0c")
public class Primary {
    @objid ("20e9ebdb-82d9-4940-8493-bc96f23645bd")
    public static final String STEREOTYPE_NAME = "primary";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("56277767-808c-4658-bf33-f1ad5d9829ae")
    protected final Actor elt;

    /**
     * Tells whether a {@link Primary proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << primary >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8aa0c0fa-6798-4114-b89d-f1f2e43ef114")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Primary.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << primary >> then instantiate a {@link Primary} proxy.
     * 
     * @return a {@link Primary} proxy on the created {@link Actor}.
     */
    @objid ("a9ad540c-8820-4b4d-a72e-c4159aab51c9")
    public static Primary create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Primary.STEREOTYPE_NAME);
        return Primary.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link Primary} proxy from a {@link Actor} stereotyped << primary >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link Primary} proxy or <i>null</i>.
     */
    @objid ("b967b3ff-f44b-41d7-80c4-cc732ddd59ba")
    public static Primary instantiate(Actor obj) {
        return Primary.canInstantiate(obj) ? new Primary(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Primary} proxy from a {@link Actor} stereotyped << primary >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Actor}
     * @return a {@link Primary} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f19844c1-443f-4fe0-9766-9088546e1db3")
    public static Primary safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (Primary.canInstantiate(obj))
        	return new Primary(obj);
        else
        	throw new IllegalArgumentException("Primary: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("faa3e758-fdd5-4f0b-bd0f-5a36dd7bf285")
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
        Primary other = (Primary) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Actor}. 
     * @return the Actor represented by this proxy, never null.
     */
    @objid ("17280306-c355-4c82-83cc-9339bcb307ad")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("d79ce389-d1f1-4d56-b977-3c7050f378d5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8ed23f4e-8a16-4067-8243-1b4a002e8fa9")
    protected Primary(Actor elt) {
        this.elt = elt;
    }

    @objid ("1ae3dee4-02d9-4030-b240-c2d466e4b84b")
    public static final class MdaTypes {
        @objid ("c73034e6-67f4-4a7c-91d4-db8525a8076f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ad8181ec-db93-4081-88f4-9c4aa21d636e")
        private static Stereotype MDAASSOCDEP;

        @objid ("cfcd15c4-f0af-46d5-85f7-1b871310adcc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("45a7949c-ad55-49ca-93f6-04e5f3ed91f2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2ef9-0000-000000000000");
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
