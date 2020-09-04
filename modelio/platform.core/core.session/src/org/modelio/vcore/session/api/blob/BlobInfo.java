/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.session.api.blob;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Information about a blob.
 * <p>
 * Instances of this classe are immutable.
 * Blob informations can be read/written on any {@link InputStream} / {@link OutputStream}.
 */
@objid ("9abe7eee-1f62-4ef8-9472-da87332f5721")
public class BlobInfo implements IBlobInfo {
    @objid ("f2dfbae7-98d5-4558-a2fb-31518737d620")
    private final String key;

    @objid ("93426024-0cf2-479b-9600-b45fc8690788")
    private final String localName;

    @objid ("a8278197-d3ab-48d0-ae24-f45aec0d4e77")
    private final MRef relatedRef;

    /**
     * @param key the blob key
     * @param label the blob label - not used anymore
     * @deprecated since 3.7 use {@link #BlobInfo(String)}
     */
    @objid ("6fb9e474-0e38-4091-b91e-e91dfa4bfa74")
    @Deprecated
    public BlobInfo(String key, String label) {
        this(key);
    }

    /**
     * Copy constructor
     * 
     * @param other the blob info to copy.
     */
    @objid ("7228adb5-b5b3-4ef3-b33e-00093ffd7258")
    public BlobInfo(BlobInfo other) {
        this.key = other.getKey();
        this.relatedRef = other.getRelatedElement();
        this.localName = other.localName;
    }

    /**
     * Constructs a global blob info.
     * 
     * @param key the blob key
     * @since 3.7
     */
    @objid ("56bd9a32-1a20-46d5-86a2-d7fcce5053cb")
    public BlobInfo(String key) {
        this.key = Objects.requireNonNull(key, "key is null");
        this.relatedRef = null;
        this.localName = null;
    }

    /**
     * @param label a user friendly label.
     * @since 3.7
     * 
     * @param owner the owner model element
     * @param localName an string to identify this blob in the blobs owned by the owned element. Will be used to compute the blob key.
     */
    @objid ("8de2fa4c-58f4-40bd-9ed3-1134c96801ed")
    public BlobInfo(MRef owner, String localName) {
        this.relatedRef = Objects.requireNonNull(owner);
        this.localName = Objects.requireNonNull(localName);
        this.key = computeKey(this.relatedRef,localName);
    }

    /**
     * Compute a global key from an optional MRef and and optional local identifier.
     * <p>
     * If the MRef is null the local name is returned.
     * If the local name is null a prefix string is returned.
     * 
     * @param ref a model object reference
     * @param localName a local name.
     * @return the computed key or key prefix.
     * @since 3.7
     */
    @objid ("c5aeb6b7-529f-45b5-a5ab-cdb2eb15056b")
    public static String computeKey(MRef ref, String localName) {
        if (ref==null) {
            return localName;
        } else if (localName == null){
            // returns a prefix
            return ref.uuid + "-" ;
        } else {
            return ref.uuid + "-" + localName;
        }
    }

    /**
     * Compute a global key from an optional MObject and and optional local identifier.
     * <p>
     * If the MObject is null the local name is returned.
     * If the local name is null a prefix string is returned.
     * 
     * @param obj a model object
     * @param localName a local name.
     * @return the computed key or key prefix.
     * @since 3.7
     */
    @objid ("12acbb0b-68e3-4ef4-a03f-a51d8eb14f5a")
    public static String computeKey(MObject obj, String localName) {
        if (obj==null) {
            return localName;
        } else if (localName == null){
            // returns a prefix
            return obj.getUuid() + "-" ;
        } else {
            return obj.getUuid() + "-" + localName;
        }
    }

    @objid ("934a1d77-d941-4049-8391-d8e6edea1d5b")
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
        BlobInfo other = (BlobInfo) obj;
        if (this.key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!this.key.equals(other.key)) {
            return false;
        }
        if (this.relatedRef == null) {
            if (other.relatedRef != null) {
                return false;
            }
        } else if (!this.relatedRef.equals(other.relatedRef)) {
            return false;
        }
        return true;
    }

    @objid ("9ab16808-d70f-47c3-975e-5e828edb535c")
    @Override
    public String getKey() {
        return this.key;
    }

    @objid ("5a35c2f8-ee5f-4b07-baba-d796aa79a0c3")
    @Override
    @Deprecated
    public String getLabel() {
        return getKey();
    }

    @objid ("62cfae1a-1712-4f5e-ae55-dc2bd6288267")
    @Override
    public String getLocalName() {
        return this.localName;
    }

    @objid ("3a6ae44f-ce0d-4ba3-b178-113f3720a6e7")
    @Override
    public MRef getRelatedElement() {
        return this.relatedRef;
    }

    @objid ("ca90191c-c074-4008-bb0e-10c863a149ec")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.key == null) ? 0 : this.key.hashCode());
        return result;
    }

    @objid ("10f06429-4ee1-4558-bc19-d08091073baf")
    @Override
    public String toString() {
        return "'"+getKey()+"' "+getClass().getSimpleName();
    }

}
