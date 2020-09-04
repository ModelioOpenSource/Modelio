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
 * Proxy class to handle a {@link MethodologicalLink} with << Process >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Le drag & drop d’un processus BPMN doit faire apparaitre un Business Process dans les diagrammes Archimate.</i></p>
 */
@objid ("16535a93-7544-4b0e-b4e9-66d8056f81a0")
public class Process {
    @objid ("851988e3-0eef-4e47-b60c-63fc0ee5b7d9")
    public static final String STEREOTYPE_NAME = "Process";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("a12ca427-02c5-49d8-b209-b120aa128264")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Process proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Process >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("99ef66ad-6433-4b42-b781-b433f09bb810")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Process >> then instantiate a {@link Process} proxy.
     * 
     * @return a {@link Process} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("c54c7375-80da-43af-b8cc-07c399184c48")
    public static Process create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME);
        return Process.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link MethodologicalLink} stereotyped << Process >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Process} proxy or <i>null</i>.
     */
    @objid ("5949917b-94bb-4ac0-b8b3-d257fd9b2f55")
    public static Process instantiate(MethodologicalLink obj) {
        return Process.canInstantiate(obj) ? new Process(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link MethodologicalLink} stereotyped << Process >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Process} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("eba36cd7-8cc7-4952-bba8-1eb691fb7cb0")
    public static Process safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Process.canInstantiate(obj))
        	return new Process(obj);
        else
        	throw new IllegalArgumentException("Process: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9caff0aa-aa63-4353-b660-5528c1dd3287")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("84395eea-8463-40d1-a1b1-d270f7c50538")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("3b658680-0cd9-4b29-bde8-89d7cbaec89a")
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
        Process other = (Process) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("dd86ad55-c29f-46ee-8742-e6cba34fe407")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("fc88e204-ddbe-4a38-af35-84aacf8dc85f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e2d6bb26-5ac7-4e84-ade1-0490e40842e4")
    protected Process(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("86a4b530-cc53-4296-b4ec-0c0ed3b5a15a")
    public static final class MdaTypes {
        @objid ("a1f9656c-214a-45b0-94c0-95f69033a426")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2d67bf14-e1d1-4b63-ae28-a772786e3f55")
        private static Stereotype MDAASSOCDEP;

        @objid ("dd980d8a-a97c-4e42-b22d-bc9f47c9b15e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9c99b417-89fa-4113-b01f-305194564ea0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "616b72d4-1d47-49e1-a381-2e6ecfea637c");
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
