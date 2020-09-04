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
 * Proxy class to handle a {@link Actor} with << secondary >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5214e11f-81b6-4d62-9a5c-3032b96258c1")
public class Secondary {
    @objid ("ef853430-d7c4-40a0-88e9-956affdfbb57")
    public static final String STEREOTYPE_NAME = "secondary";

    /**
     * The underlying {@link Actor} represented by this proxy, never null.
     */
    @objid ("32428ac9-c50f-4afc-9ba3-4032ae02f88b")
    protected final Actor elt;

    /**
     * Tells whether a {@link Secondary proxy} can be instantiated from a {@link MObject} checking it is a {@link Actor} stereotyped << secondary >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("361da92f-7433-4896-a7f6-9c7558a5d84b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Actor) && ((Actor) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Secondary.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Actor} stereotyped << secondary >> then instantiate a {@link Secondary} proxy.
     * 
     * @return a {@link Secondary} proxy on the created {@link Actor}.
     */
    @objid ("afc89f4b-37e8-4e1b-b6b0-6d77951dbc6f")
    public static Secondary create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Actor");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Secondary.STEREOTYPE_NAME);
        return Secondary.instantiate((Actor)e);
    }

    /**
     * Tries to instantiate a {@link Secondary} proxy from a {@link Actor} stereotyped << secondary >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Actor
     * @return a {@link Secondary} proxy or <i>null</i>.
     */
    @objid ("514f7e7a-7ee3-47e5-87f2-abd3683d81c3")
    public static Secondary instantiate(Actor obj) {
        return Secondary.canInstantiate(obj) ? new Secondary(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Secondary} proxy from a {@link Actor} stereotyped << secondary >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Actor}
     * @return a {@link Secondary} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9fd32e69-e790-40e8-9c7c-72b86b6dbb70")
    public static Secondary safeInstantiate(Actor obj) throws IllegalArgumentException {
        if (Secondary.canInstantiate(obj))
        	return new Secondary(obj);
        else
        	throw new IllegalArgumentException("Secondary: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("852a27cc-8efd-41e0-b3c1-1c48ade1c3d1")
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
        Secondary other = (Secondary) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Actor}. 
     * @return the Actor represented by this proxy, never null.
     */
    @objid ("6c0882c2-5479-45da-955c-4f38e0a33553")
    public Actor getElement() {
        return this.elt;
    }

    @objid ("38a0269b-c92d-4d7f-bb52-f7518527233d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fc15ff81-e2ba-4daa-bf21-30cc9626fb3c")
    protected Secondary(Actor elt) {
        this.elt = elt;
    }

    @objid ("8e474651-cd0e-4098-985c-82cba46299e9")
    public static final class MdaTypes {
        @objid ("2be61049-f0e6-4633-9d82-fd35356166ba")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("297818ea-aa93-4c76-9603-d49e14a37a96")
        private static Stereotype MDAASSOCDEP;

        @objid ("da7a5f39-c543-40c1-b083-ff644f1d0fc3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("69491b14-a72c-47d8-bd34-37857e0a973d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2f04-0000-000000000000");
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
