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
 * Proxy class to handle a {@link MethodologicalLink} with << State >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("94ea7c34-4b73-4981-b50a-54bf698ba624")
public class State {
    @objid ("7c030b49-7172-44f2-a9c7-91b1de089271")
    public static final String STEREOTYPE_NAME = "State";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("adc0d746-0cb6-46d0-a020-f5f40cf7863e")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link State proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << State >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1ec5ba3d-1a8c-4407-b043-92dc39330c09")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, State.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << State >> then instantiate a {@link State} proxy.
     * 
     * @return a {@link State} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("f6029d25-095a-4c31-9571-86200321ded3")
    public static State create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, State.STEREOTYPE_NAME);
        return State.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link State} proxy from a {@link MethodologicalLink} stereotyped << State >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link State} proxy or <i>null</i>.
     */
    @objid ("28f0c5ab-4d6b-476f-8473-357c89597ea1")
    public static State instantiate(MethodologicalLink obj) {
        return State.canInstantiate(obj) ? new State(obj) : null;
    }

    /**
     * Tries to instantiate a {@link State} proxy from a {@link MethodologicalLink} stereotyped << State >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link State} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9e87b31d-95b9-42f7-8d42-0b4f78b7b54e")
    public static State safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (State.canInstantiate(obj))
        	return new State(obj);
        else
        	throw new IllegalArgumentException("State: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7207f726-3712-4dda-b4f6-7c3b2a15ecab")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("a4f6612b-2466-4a91-bf27-450b83b67491")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("5f936653-777c-4ed4-9d1d-abdd681a645f")
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
        State other = (State) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("75151b82-fd92-4f0d-b655-914c5b97ada1")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("a0dfb1d9-efa0-4a12-81aa-733fc13313ab")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("112c752f-7cb1-43ec-87d7-101ec5718032")
    protected State(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("21c2a2ed-24af-4840-be82-7c2e0a995f1c")
    public static final class MdaTypes {
        @objid ("5a51b518-cea4-4a1b-ab73-323baff17302")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e7ee3f2a-cb6b-406f-b495-d499df01fab0")
        private static Stereotype MDAASSOCDEP;

        @objid ("cc1bd15f-1fc7-47a8-870d-7582050fde03")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8e481204-1ff0-4bef-8e3d-e23e932d5b71")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c2d2a1ec-2c29-453c-a79c-19e4f2d27f13");
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
