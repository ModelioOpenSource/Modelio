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
 * Proxy class to handle a {@link MethodologicalLink} with << Event >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Drag & drop Business Event ou Application ou Technology Event dans BPMN fait apparaître un événement BPMN</i></p>
 */
@objid ("cf8c7e41-0c2a-41b4-9804-c91c0266763d")
public class Event {
    @objid ("72910556-d639-49c1-9596-f1403a4e9f6f")
    public static final String STEREOTYPE_NAME = "Event";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("36312be5-a303-4d8d-9516-ee2ac3f2a16b")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Event proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Event >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("dc7da13c-c866-48dc-9c6f-fb7411d71cbc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Event.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Event >> then instantiate a {@link Event} proxy.
     * 
     * @return a {@link Event} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("d237de70-0d1e-4f2d-b990-fa231647145c")
    public static Event create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Event.STEREOTYPE_NAME);
        return Event.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Event} proxy from a {@link MethodologicalLink} stereotyped << Event >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Event} proxy or <i>null</i>.
     */
    @objid ("d705e550-63b0-49c3-98fc-258427ab3f5f")
    public static Event instantiate(MethodologicalLink obj) {
        return Event.canInstantiate(obj) ? new Event(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Event} proxy from a {@link MethodologicalLink} stereotyped << Event >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Event} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("dea1dd01-09f5-4a36-98e6-a9328edb0c9f")
    public static Event safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Event.canInstantiate(obj))
        	return new Event(obj);
        else
        	throw new IllegalArgumentException("Event: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0f389cd8-7e96-4028-97c1-403e9e0020b6")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("f7ab7bde-1a5a-48e4-831d-c0db79ec3fed")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("389562d8-c0d4-4592-ac08-cceac3e8a75b")
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
        Event other = (Event) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("80092898-086c-425b-82bc-bcc25e650ae5")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("34f4d9c4-21f5-4efc-91f0-817fc3bcd592")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("56808da7-a375-4581-8326-b49c73e4e36b")
    protected Event(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("24c9a10e-2f9d-4fb5-9699-ecce4cc02405")
    public static final class MdaTypes {
        @objid ("415655c8-06b0-4289-be2b-d7307aeede19")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("32288e26-7601-42ed-a55e-7ca072b551dc")
        private static Stereotype MDAASSOCDEP;

        @objid ("a7eb2033-d398-4e0c-9497-f563e454eb36")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a09d8f30-0e9e-4052-8757-b612948217b1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "143b4e00-fe2e-44d0-9c64-5a95e385ec5a");
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
