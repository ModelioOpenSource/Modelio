/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.artifact;

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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Artifact} with << document >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("598e9650-6e45-49b6-90c3-c582c258cc1a")
public class Document {
    @objid ("4481c13d-0a78-4ab4-b6e1-c68a07b35745")
    public static final String STEREOTYPE_NAME = "document";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("7e9bdaa3-45af-406e-80b5-bc3a2f81f9a2")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Document proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << document >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f0874f7e-a399-448a-b74e-ce090463d7d1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Document.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << document >> then instantiate a {@link Document} proxy.
     * 
     * @return a {@link Document} proxy on the created {@link Artifact}.
     */
    @objid ("3bc5ca5c-57ee-4def-8c8b-db6191a39a43")
    public static Document create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Document.STEREOTYPE_NAME);
        return Document.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Document} proxy from a {@link Artifact} stereotyped << document >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Document} proxy or <i>null</i>.
     */
    @objid ("1b11cd13-3e4b-4d3b-911b-cdd1df139555")
    public static Document instantiate(Artifact obj) {
        return Document.canInstantiate(obj) ? new Document(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Document} proxy from a {@link Artifact} stereotyped << document >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Document} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d0ba360e-b13d-4c67-8c67-575b88f1fd88")
    public static Document safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Document.canInstantiate(obj))
        	return new Document(obj);
        else
        	throw new IllegalArgumentException("Document: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("88b9cd58-7d96-47c3-a55a-441074605d55")
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
        Document other = (Document) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("e32a0368-05b9-47d3-9e53-f1f1c5088ae4")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("62320a7b-2565-4369-8db0-c2ec1a945cd3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("cd9bfe3d-56cb-4f32-acb1-c05e140780c1")
    protected Document(Artifact elt) {
        this.elt = elt;
    }

    @objid ("fe2f2ca6-6273-4ced-825f-6e1d068d5a3b")
    public static final class MdaTypes {
        @objid ("6b95f9c4-f9d2-4a2a-8826-eb71be07560f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e4ec66d2-b905-4104-b4db-c48274c34af1")
        private static Stereotype MDAASSOCDEP;

        @objid ("84316c45-d971-4349-8a27-83f8602c8125")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("81620d86-8c72-4703-9a2c-03a1db54e6f4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "388ba911-9fb3-4117-80af-6099142d7816");
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
