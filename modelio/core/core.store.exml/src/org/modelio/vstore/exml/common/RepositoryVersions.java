/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.vstore.exml.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Formats versions used by a repository.
 * <p>
 * Can tell whether a repository can be read/written by this binary.
 */
@objid ("5dc65c2e-b7c3-41ec-878c-b0255c4bdb31")
public class RepositoryVersions {
    /**
     * The current repository format version.
     * <p>
     * History: <ul>
     * <li> 0 : before this class exist
     * <li> 1 : current repository format.
     * <li> 2 : metaclass directories is now the qualified metaclass name
     * </ul>
     */
    @objid ("ca97c930-e00a-4a24-9d40-7b5d80df7e15")
    public static final int CURRENT_FORMAT = 2;

    @objid ("cbd3a65f-a164-426f-9a16-2a071d088f32")
    private static final String PROP_CMSNODES = "cmsnodes";

    @objid ("84e8250b-3089-46cf-91d0-ea24fe1cb984")
    private static final String PROP_REPOSITORY_FORMAT = "repository_format";

    @objid ("1fdca20a-e7db-49eb-adec-3c43280a8e60")
    private final List<String> cmsNodesSig;

    @objid ("75d90a8a-c121-4f59-998f-d6278242e2cf")
    private final int repositoryFormat;

    /**
     * Read a {@link RepositoryVersions} written with {@link #write(OutputStream)}.
     * @param is the input stream
     * @return the RepositoryVersions
     * @throws IOException on failure
     */
    @objid ("1dd93b6b-e945-4670-95d2-49fa414097c8")
    public static RepositoryVersions fromStream(InputStream is) throws IOException {
        Properties props = new Properties();
        props.load(is);
        
        int repositoryFormat = Integer.parseInt((String) props.get(PROP_REPOSITORY_FORMAT));
        String cmsnodes_str = (String) props.get(PROP_CMSNODES);
        List<String> lcmsNodesSig = Arrays.asList(cmsnodes_str.split(","));
        return new RepositoryVersions(repositoryFormat, lcmsNodesSig);
    }

    /**
     * Constructor from fields.
     * @param format the repository format version
     * @param cmsNodesSig The CMS nodes
     */
    @objid ("c1ecc9df-9773-472c-84dd-e066d92578dd")
    public  RepositoryVersions(int format, List<String> cmsNodesSig) {
        this.repositoryFormat = format;
        this.cmsNodesSig = new ArrayList<>(cmsNodesSig);
        
    }

    /**
     * Constructor for the given metamodel.
     * @param mm a metamodel
     */
    @objid ("4c84eefd-4b78-408e-b1df-e5f66598cf19")
    public  RepositoryVersions(MMetamodel mm) {
        this (CURRENT_FORMAT, mm);
    }

    /**
     * Constructor for the given metamodel.
     * @param format the format version
     * @param mm a metamodel
     */
    @objid ("a1a0a84a-812d-4ba8-8a93-d13c42bfb6c8")
    public  RepositoryVersions(int format, MMetamodel mm) {
        final Collection<? extends MClass> registeredClasses = mm.getRegisteredMClasses();
        this.cmsNodesSig = new ArrayList<>();
        
        if (format < 2) {
            for (MClass c : registeredClasses) {
                if (c.isCmsNode()) {
                    this.cmsNodesSig.add(c.getName());
                }
            }
        } else {
            for (MClass c : registeredClasses) {
                if (c.isCmsNode() && ! c.isAbstract()) {
                    this.cmsNodesSig.add(c.getQualifiedName());
                }
            }
        }
        Collections.sort(this.cmsNodesSig);
        
        
        this.repositoryFormat = format;
        
    }

    /**
     * Check whether this version is compatible with the project one.
     * @param mm the project metamodel
     * @throws IncompatibleVersionException if the version is not compatible.
     */
    @objid ("8f0136d2-ecb7-42ca-8d52-bcc4a469497e")
    public void checkCompatible(MMetamodel mm) throws IncompatibleVersionException {
        checkCompatible(new RepositoryVersions(mm));
    }

    /**
     * @return all CMS node metaclasses known by this repository.
     * @since 3.6.1
     */
    @objid ("3b6e91d7-d3f2-477f-ba18-0bf3093329a4")
    public Collection<String> getCmsNodeMetaclasses() {
        return this.cmsNodesSig;
    }

    /**
     * @return the repository format version.
     */
    @objid ("c59f67e0-6c7b-4614-9047-cdd3bc899f6e")
    public int getRepositoryFormat() {
        return this.repositoryFormat;
    }

    /**
     * Write this version in a property map
     * @param properties the write destination
     */
    @objid ("5c9a1281-2a9e-4af9-b09c-4769db46e32f")
    protected void write(Map<Object, Object> properties) {
        StringBuilder s = new StringBuilder(this.cmsNodesSig.size() * 10);
        for (String c : this.cmsNodesSig) {
            s.append(c);
            s.append(',');
        }
        
        properties.put(PROP_CMSNODES, s.toString());
        
        properties.put(PROP_REPOSITORY_FORMAT, String.valueOf(this.repositoryFormat));
        
    }

    /**
     * Write this version in the given stream in the format read by {@link #repositoryFormat} .
     * @param out where to write this version.
     * @throws IOException on I/O failure
     */
    @objid ("6dffdcdb-930c-4a44-a5f9-c4dfcc77de0d")
    public void write(OutputStream out) throws IOException {
        Properties props = new Properties();
        write(props);
        props.store(out, "Repository format version, DO NOT EDIT.");
        
    }

    @objid ("f1446c54-34d5-413f-9e98-817fcc09fc6a")
    private void checkCompatible(RepositoryVersions reference) throws IncompatibleVersionException {
        if (this.repositoryFormat != reference.repositoryFormat) {
            if (reference.getRepositoryFormat() == 2 && this.repositoryFormat < 2) {
                // format 0 and 1 are compatible with 2, continue.
            } else {
                throw new IncompatibleVersionException("Repository format "+this.repositoryFormat+" is incompatible with "+reference.repositoryFormat+ " version.");
            }
        }
        
    }

    /**
     * Tells the repository format is not compatible with the current format.
     */
    @objid ("1c14b4bf-ede9-44b0-b075-f83c7294786c")
    public static class IncompatibleVersionException extends IOException {
        @objid ("ce27da4d-9233-4d87-8f1c-bdadeaf14bd6")
        private static final long serialVersionUID = 1L;

        /**
         * Constructs an <code>IncompatibleVersionException</code> with the specified detail message.
         * @param message the message
         */
        @objid ("aee7c652-11d3-4734-bb35-5b661972a21e")
        public  IncompatibleVersionException(String message) {
            super(message);
        }

    }

}
