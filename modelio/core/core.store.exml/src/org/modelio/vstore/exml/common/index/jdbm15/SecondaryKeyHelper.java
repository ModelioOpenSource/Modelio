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
package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.InverseHashView;
import jdbm.PrimaryHashMap;
import jdbm.RecordListener;
import jdbm.SecondaryHashMap;
import jdbm.SecondaryKeyExtractor;
import jdbm.SecondaryTreeMap;
import jdbm.Serializer;
import jdbm.btree.BTree;
import jdbm.btree.BTreeSecondarySortedMap;
import jdbm.helper.ComparableComparator;
import jdbm.helper.JdbmBase;
import jdbm.htree.HTree;
import jdbm.htree.HTreeSecondaryMap;

@objid ("681daba8-5403-4eb4-9805-3486abdfcf01")
final class SecondaryKeyHelper {
    @objid ("62214746-2df3-402e-b87e-5c7a56a1de55")
    public static <A, K, V> BTree<A, Iterable<K>> secondaryBTree(final String objectName, final SecondaryKeyExtractor<A, K, V> keyExtractor, final Comparator<A> comparator, final JdbmBase<K, V> b, final Serializer<A> secondaryKeySerializer, final Serializer<Iterable<K>> keysSerializer) throws IOException {
        BTree<A,Iterable<K>> secIndex = null;
        long recid = b.getRecordManager().getNamedObject( objectName );
        if ( recid != 0 ) {
            secIndex = BTree.load(b.getRecordManager(), recid );
        } else {
            secIndex = BTree.createInstance( b.getRecordManager(), comparator );
            b.getRecordManager().setNamedObject( objectName, secIndex.getRecid() );
        }
        
        secIndex.setKeySerializer(secondaryKeySerializer);
        secIndex.setValueSerializer(keysSerializer);
        
        
        //second final variable so it can be accesed from listener
        final BTree<A,Iterable<K>> secIndex2 = secIndex;
        
        b.addRecordListener(new RecordListener<K, V>() {
        
            @Override
            public void recordInserted(K key, V value) throws IOException {
                A secKey = keyExtractor.extractSecondaryKey(key,value);
                if(secKey == null) {
                    return;
                }
                List<K> kk = (List<K>) secIndex2.find(secKey);
                if(kk == null) {
                    kk = new ArrayList<>();
                }
                kk.add(key);
                secIndex2.insert(secKey, kk, true);
            }
        
            @Override
            public void recordRemoved(K key, V value) throws IOException {
                A secKey = keyExtractor.extractSecondaryKey(key,value);
                List<K> kk = (List<K>) secIndex2.find(secKey);
                if(kk == null) {
                    return;
                }
                kk.remove(key);
                if(kk.isEmpty()) {
                    secIndex2.remove(secKey);
                } else {
                    secIndex2.insert(secKey, kk, true);
                }                
            }
        
            @SuppressWarnings("null")
            @Override
            public void recordUpdated(K key, V oldValue, V newValue)
                    throws IOException {
                A oldSecKey = keyExtractor.extractSecondaryKey(key, oldValue);
                A newSecKey = keyExtractor.extractSecondaryKey(key, newValue);
                if(oldSecKey==null && newSecKey == null) {
                    return;
                } else if(oldSecKey==null && newSecKey!=null){
                    //insert new record
                    recordInserted(key, newValue);
                    return;
                }
                else if(oldSecKey!=null && newSecKey==null){
                    //delete old record
                    recordRemoved(key, oldValue);
                    return;
                }
                else if(oldSecKey.equals(newSecKey)) {
                    //both keys are equal, nothing
                    return;
                }
        
                //remove old key
                recordRemoved(key, oldValue);
                //insert new key
                recordInserted(key,newValue);                
            }
        });
        return secIndex;
    }

    @objid ("15fa5360-a99b-4802-9461-cf573497706e")
    public static <A, K, V> HTree<A, Iterable<K>> secondaryHTree(final String objectName, final SecondaryKeyExtractor<A, K, V> keyExtractor, final JdbmBase<K, V> b, final Serializer<A> secondaryKeySerializer, final Serializer<Iterable<K>> keysSerializer) throws IOException {
        HTree<A,Iterable<K>> secIndex = null;
        long recid = b.getRecordManager().getNamedObject( objectName );
        if ( recid != 0 ) {
            secIndex = HTree.load(b.getRecordManager(), recid,secondaryKeySerializer, keysSerializer );
        } else {
            secIndex = HTree.createInstance( b.getRecordManager(),secondaryKeySerializer,keysSerializer);
            b.getRecordManager().setNamedObject( objectName, secIndex.getRecid() );
        }
        
        //second final variable so it can be accesed from listener
        final HTree<A,Iterable<K>> secIndex2 = secIndex;
        
        b.addRecordListener(new RecordListener<K, V>() {
        
            @Override
            public void recordInserted(K key, V value) throws IOException {
                A secKey = keyExtractor.extractSecondaryKey(key,value);
                if(secKey == null) {
                    return;
                }
                List<K> kk = (List<K>) secIndex2.find(secKey);
                if(kk == null) {
                    kk = new ArrayList<>();
                }
                kk.add(key);
                secIndex2.put(secKey, kk);
            }
        
            @Override
            public void recordRemoved(K key, V value) throws IOException {
                A secKey = keyExtractor.extractSecondaryKey(key,value);
                List<K> kk = (List<K>) secIndex2.find(secKey);
                if(kk == null) {
                    return;
                }
                kk.remove(key);
                if(kk.isEmpty()) {
                    secIndex2.remove(secKey);
                } else {
                    secIndex2.put(secKey, kk);
                }                
            }
        
            @SuppressWarnings("null")
            @Override
            public void recordUpdated(K key, V oldValue, V newValue)
                    throws IOException {
                A oldSecKey = keyExtractor.extractSecondaryKey(key,oldValue);
                A newSecKey = keyExtractor.extractSecondaryKey(key,newValue);
        
                if(oldSecKey==null && newSecKey == null) {
                    return;
                } else if(oldSecKey==null && newSecKey!=null){
                    //insert new record
                    recordInserted(key, newValue);
                    return;
                }
                else if(oldSecKey!=null && newSecKey==null){
                    //delete old record
                    recordRemoved(key, oldValue);
                    return;
                }
                else if(oldSecKey.equals(newSecKey)) {
                    //both keys are equal, nothing
                    return;
                }
        
                //remove old key
                recordRemoved(key, oldValue);
                //insert new key
                recordInserted(key,newValue);                
            }
        });
        return secIndex;
    }

    @objid ("f3f91462-469d-4f3b-9384-7438a853e861")
    public static <A, K, V> BTree<A, Iterable<K>> secondaryBTreeManyToOne(final String objectName, final SecondaryKeyExtractor<Iterable<A>, K, V> keyExtractor, final Comparator<A> comparator, final JdbmBase<K, V> b) throws IOException {
        BTree<A,Iterable<K>> secIndex = null;
        long recid = b.getRecordManager().getNamedObject( objectName );
        if ( recid != 0 ) {
            secIndex = BTree.load(b.getRecordManager(), recid );
        } else {
            secIndex = BTree.createInstance( b.getRecordManager(), comparator );
            b.getRecordManager().setNamedObject( objectName, secIndex.getRecid() );
        }
        
        //second final variable so it can be accesed from listener
        final BTree<A,Iterable<K>> secIndex2 = secIndex;
        
        b.addRecordListener(new RecordListener<K, V>() {
        
            @Override
            public void recordInserted(K key, V value) throws IOException {
                for(A secKey : keyExtractor.extractSecondaryKey(key,value)){
                    if(secKey == null) {
                        return;
                    }
                    List<K> kk = (List<K>) secIndex2.find(secKey);
                    if(kk == null) {
                        kk = new ArrayList<>();
                    }
                    kk.add(key);
                    secIndex2.insert(secKey, kk, true);
                }
            }
        
            @Override
            public void recordRemoved(K key, V value) throws IOException {
                for(A secKey : keyExtractor.extractSecondaryKey(key,value)){
                    List<K> kk = (List<K>) secIndex2.find(secKey);
                    if(kk == null) {
                        return;
                    }
                    kk.remove(key);
                    if(kk.isEmpty()) {
                        secIndex2.remove(secKey);
                    } else {
                        secIndex2.insert(secKey, kk, true);
                    }
                }
            }
        
            @SuppressWarnings("null")
            @Override
            public void recordUpdated(K key, V oldValue, V newValue)
                    throws IOException {
                Iterable<A> oldSecKey = keyExtractor.extractSecondaryKey(key, oldValue);
                Iterable<A> newSecKey = keyExtractor.extractSecondaryKey(key, newValue);
                if(oldSecKey==null && newSecKey == null) {
                    return;
                }
        
                if(oldSecKey==null && newSecKey!=null){
                    //insert new record
                    recordInserted(key, newValue);
                    return;
                }
                if(oldSecKey!=null && newSecKey==null){
                    //delete old record
                    recordRemoved(key, oldValue);
                    return;
                }
        
                if(oldSecKey.equals(newSecKey)) {
                    //both keys are equal, nothing
                    return;
                }
        
                //remove old key
                recordRemoved(key, oldValue);
                //insert new key
                recordInserted(key,newValue);                
            }
        });
        return secIndex;
    }

    @objid ("05c9f5a9-3815-4e3e-b31a-09fe45a8e560")
    public static <A, K, V> HTree<A, Iterable<K>> secondaryHTreeManyToOne(final String objectName, final SecondaryKeyExtractor<Iterable<A>, K, V> keyExtractor, final JdbmBase<K, V> b, final Serializer<A> secondaryKeySerializer, final Serializer<Iterable<K>> resultSerializer) throws IOException {
        HTree<A,Iterable<K>> secIndex = null;
        long recid = b.getRecordManager().getNamedObject( objectName );
        if ( recid != 0 ) {
            secIndex = HTree.load(b.getRecordManager(), recid,secondaryKeySerializer,resultSerializer );
        } else {
            secIndex = HTree.createInstance( b.getRecordManager(),secondaryKeySerializer,resultSerializer);
            b.getRecordManager().setNamedObject( objectName, secIndex.getRecid() );
        }
        
        //second final variable so it can be accesed from listener
        final HTree<A,Iterable<K>> secIndex2 = secIndex;
        
        b.addRecordListener(new RecordListener<K, V>() {
        
            @Override
            public void recordInserted(K key, V value) throws IOException {
                for(A secKey : keyExtractor.extractSecondaryKey(key,value)){
                    if(secKey == null) {
                        return;
                    }
                    List<K> kk = (List<K>) secIndex2.find(secKey);
                    if(kk == null) {
                        kk = new ArrayList<>();
                    }
                    kk.add(key);
                    secIndex2.put(secKey, kk); //TODO here
                }
            }
        
            @Override
            public void recordRemoved(K key, V value) throws IOException {
                for(A secKey : keyExtractor.extractSecondaryKey(key,value)){
                    List<K> kk = (List<K>) secIndex2.find(secKey);
                    if(kk == null) {
                        return;
                    }
                    kk.remove(key);
                    if(kk.isEmpty()) {
                        secIndex2.remove(secKey);
                    } else {
                        secIndex2.put(secKey, kk);
                    }
                }
            }
        
            @SuppressWarnings("null")
            @Override
            public void recordUpdated(K key, V oldValue, V newValue)
                    throws IOException {
                Iterable<A> oldSecKey = keyExtractor.extractSecondaryKey(key,oldValue);
                Iterable<A> newSecKey = keyExtractor.extractSecondaryKey(key,newValue);
                if(oldSecKey==null && newSecKey == null) {
                    return;
                }
        
                if(oldSecKey==null && newSecKey!=null){
                    //insert new record
                    recordInserted(key, newValue);
                    return;
                }
                if(oldSecKey!=null && newSecKey==null){
                    //delete old record
                    recordRemoved(key, oldValue);
                    return;
                }
        
                if(oldSecKey.equals(newSecKey)) {
                    //both keys are equal, nothing
                    return;
                }
        
                //remove old key
                recordRemoved(key, oldValue);
                //insert new key
                recordInserted(key,newValue);                
            }
        });
        return secIndex;
    }

    @objid ("f3fec822-d494-4154-b8ea-7df52c917134")
    public static <A, K, V> SecondaryHashMap<A, K, V> secondaryHashMap(final String objectName, final SecondaryKeyExtractor<A, K, V> secKeyExtractor, final JdbmBase<K, V> b, final Serializer<A> secondaryKeySerializer, final Serializer<Iterable<K>> keysSerializer) {
        try{
            HTree<A,Iterable<K>> secTree = secondaryHTree(objectName, secKeyExtractor, b,secondaryKeySerializer, keysSerializer);
            HTreeSecondaryMap<A, K, V> ret = new HTreeSecondaryMap<>(secTree, b);
            return ret;
        }catch (IOException e){
            throw new IOError(e);
        }
        
    }

    @objid ("8684fd8f-7fae-4a33-a538-d218bc5ec1f8")
    public static <A, K, V> SecondaryTreeMap<A, K, V> secondaryTreeMap(final String objectName, final SecondaryKeyExtractor<A, K, V> secKeyExtractor, final Comparator<A> comparator, final JdbmBase<K, V> b, final Serializer<A> secondaryKeySerializer, final Serializer<Iterable<K>> keysSerializer) {
        try{
            BTree<A,Iterable<K>> secTree = secondaryBTree(objectName, secKeyExtractor, comparator,b, secondaryKeySerializer, keysSerializer);
        
            BTreeSecondarySortedMap<A, K, V> ret = new BTreeSecondarySortedMap<>(secTree, b);
            return ret;
        }catch (IOException e){
            throw new IOError(e);
        }
        
    }

    @objid ("e6e63c73-9a46-40ce-a975-375aed8a0f9e")
    public static <A, K, V> SecondaryHashMap<A, K, V> secondaryHashMapManyToOne(final String objectName, final SecondaryKeyExtractor<Iterable<A>, K, V> secKeyExtractor, final JdbmBase<K, V> b, final Serializer<A> secondaryKeySerializer, final Serializer<Iterable<K>> secondaryValueSerializer) {
        try{
            HTree<A,Iterable<K>> secTree = secondaryHTreeManyToOne(objectName, secKeyExtractor, b,secondaryKeySerializer, secondaryValueSerializer);
            HTreeSecondaryMap<A, K, V> ret = new HTreeSecondaryMap<>(secTree, b);
            return ret;
        }catch (IOException e){
            throw new IOError(e);
        }
        
    }

    @objid ("c12ebc53-9dce-4927-85bd-5472a060cce6")
    public static <A, K, V> SecondaryTreeMap<A, K, V> secondarySortedMapManyToOne(final String objectName, final SecondaryKeyExtractor<Iterable<A>, K, V> secKeyExtractor, final Comparator<A> comparator, final JdbmBase<K, V> b, final Serializer<A> secondaryKeySerializer) {
        try{
            BTree<A,Iterable<K>> secTree = secondaryBTreeManyToOne(objectName, secKeyExtractor, comparator,b);
            if(secondaryKeySerializer!=null) {
                secTree.setKeySerializer(secondaryKeySerializer);
            }
            BTreeSecondarySortedMap<A, K, V> ret = new BTreeSecondarySortedMap<>(secTree, b);
            return ret;
        }catch (IOException e){
            throw new IOError(e);
        }
        
    }

    @objid ("4af8c559-b6e3-4862-913b-bd2544f8970c")
    @SuppressWarnings("unchecked")
    public static <K, V> InverseHashView<K, V> inverseHashView(final PrimaryHashMap<K, V> base, final String recordName, final Serializer<Iterable<K>> keysSerializer) {
        SecondaryKeyExtractor<Integer,K,V> hashExtractor = new SecondaryKeyExtractor<Integer, K, V>() {
            long hashEqualsIdentityCounter;
            @Override
            public Integer extractSecondaryKey(K key, V value) {
                int hashCode = value.hashCode();
                //little check to protect from hashCode not being overriden
                int identityHashCode = System.identityHashCode(value);
                if(hashCode == identityHashCode) {
                    this.hashEqualsIdentityCounter++;
                } else if(this.hashEqualsIdentityCounter > 0) {
                    this.hashEqualsIdentityCounter--;
                }
                //fail if too many objects had hash equal to identityHashCode
                if(this.hashEqualsIdentityCounter > 50) {
                    throw new IllegalArgumentException("Object does not implement hashCode() correctly: "+value.getClass());
                }
                return Integer.valueOf(hashCode);
            }
        };
        
        final SecondaryTreeMap<Integer, K, V> inverseMap = secondaryTreeMap(recordName,hashExtractor,ComparableComparator.INSTANCE,base,null, keysSerializer);
        return new InverseHashView<K, V>() {
                    @Override
                    public K findKeyForValue(V val) {
                        Iterable<K> keys = inverseMap.get(Integer.valueOf(val.hashCode()));
                        if(keys == null) {
                            return null;
                        }
                        for(K k:keys){
                            if(val.equals(inverseMap.getPrimaryValue(k))) {
                                return k;
                            }
                        }
                        return null;
                    }
        
                    @Override
                    public Iterable<K> findKeysForValue(V val) {
                        Iterable<K> keys = inverseMap.get(Integer.valueOf(val.hashCode()));
                        if(keys == null) {
                            return null;
                        }
                        List<K> ret = new ArrayList<>();
                        for(K k:keys){
                            if(val.equals(inverseMap.getPrimaryValue(k))) {
                                ret.add(k);
                            }
                        }
                        return ret;
                    }
                };
    }

    @objid ("99068a1a-1b54-43d0-98d8-d1e585fbce75")
    public static <K, V> Iterable<V> translateIterable(final JdbmBase<K, V> b, final Iterable<K> keys) {
        if(keys==null) {
            return new ArrayList<>();
        }
        return () -> new Iterator<V>(){
        
                    Iterator<K> iter = keys.iterator();
        
                    @Override
                    public boolean hasNext() {
                        return this.iter.hasNext();
                    }
        
                    @Override
                    public V next() {
                        try {
                            return b.find(this.iter.next());
                        } catch (IOException e) {
                            throw new IOError(e);
                        }
                    }
        
                    @Override
                    public void remove() {
                        this.iter.remove();                        
                    }};
    }

}
