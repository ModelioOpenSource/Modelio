/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.classifier;

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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Classifier} with << metaclass >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0317e1ea-1cf1-4651-8c3e-910f2777fdd0")
public class Metaclass {
    @objid ("7be77ed6-9894-4ec5-880e-f765b953f1f4")
    public static final String STEREOTYPE_NAME = "metaclass";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("1b3d5d50-d537-4dc2-80de-b8163f3c897b")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Metaclass proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << metaclass >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("494aad29-7ef6-42a5-9074-36d3ba3b200d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Metaclass.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << metaclass >> then instantiate a {@link Metaclass} proxy.
     * 
     * @return a {@link Metaclass} proxy on the created {@link Classifier}.
     */
    @objid ("3d39d10e-93cd-45db-af2a-c874a180f09a")
    public static Metaclass create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Metaclass.STEREOTYPE_NAME);
        return Metaclass.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Metaclass} proxy from a {@link Classifier} stereotyped << metaclass >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Metaclass} proxy or <i>null</i>.
     */
    @objid ("fc76d178-6e03-47de-ae77-143151caf21b")
    public static Metaclass instantiate(Classifier obj) {
        return Metaclass.canInstantiate(obj) ? new Metaclass(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Metaclass} proxy from a {@link Classifier} stereotyped << metaclass >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Metaclass} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("44fdea18-4e07-4aed-857c-de404eeb2e07")
    public static Metaclass safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Metaclass.canInstantiate(obj))
        	return new Metaclass(obj);
        else
        	throw new IllegalArgumentException("Metaclass: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("88fda3ed-f167-4f17-9379-fdaccff0d841")
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
        Metaclass other = (Metaclass) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("d4cc3f83-76ce-40c0-b795-7921beaa6ebd")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("9bd24ef3-b572-4682-b272-aa7350ea3cc9")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4c51d9b6-af41-4ee6-8f58-f0c41d6ad204")
    protected Metaclass(Classifier elt) {
        this.elt = elt;
    }

    @objid ("849c2bbc-c1c1-4711-a8fa-6b1c9f242228")
    public static final class MdaTypes {
        @objid ("d089d260-25c9-4539-8ff0-3b8e12f32f04")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8bba93ea-f16e-460f-a5ca-41c6e6f5f0db")
        private static Stereotype MDAASSOCDEP;

        @objid ("a7921723-d50c-422d-bfae-c8018d323748")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("72546bd3-9144-47c4-bffd-1ff8f7f0bb91")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01bd-0000-000000000000");
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
