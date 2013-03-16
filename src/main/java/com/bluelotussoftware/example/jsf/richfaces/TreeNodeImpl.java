/*
 * Copyright 2011-2013 Blue Lotus Software, LLC.
 * Copyright 2011-2013 John Yeary.
 *
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
/*
 *  $Id$
 */
package com.bluelotussoftware.example.jsf.richfaces;

import com.google.common.collect.Iterators;
import java.io.Serializable;
import java.util.*;
import org.richfaces.model.TreeNode;

/**
 * This {@code TreeNode} implementation is based on the work done by the JBoss
 * RichFaces team for RichFaces 3 and 4. It is a simple practical implementation
 * that uses a generic type {@code T} to hold the data for the node.
 *
 * @author Nick Belaevski
 * @author John Yeary
 * @version 1.0
 */
public class TreeNodeImpl<T> implements TreeNode, Serializable {

    private T data;
    private LinkedHashMap<Object, TreeNodeImpl<T>> children = new LinkedHashMap<Object, TreeNodeImpl<T>>();
    private List<Object> keys = new ArrayList<Object>();

    public TreeNodeImpl() {
    }

    @Override
    public TreeNodeImpl<T> getChild(Object key) {
        return children.get(key);
    }

    @Override
    public int indexOf(Object key) {
        return keys.indexOf(key);
    }

    @Override
    public Iterator<Object> getChildrenKeysIterator() {
        if (isLeaf()) {
            return Iterators.emptyIterator();
        }
        return Iterators.unmodifiableIterator(keys.iterator());
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public void addChild(Object key, TreeNode child) {
        addChild(-1, key, child);
    }

    @Override
    public void insertChild(int idx, Object key, TreeNode child) {
        addChild(idx, key, child);
    }

    @Override
    public void removeChild(Object key) {
        children.remove(key);
        keys.remove(key);
    }

    ///////////////////////////
    // Non Interface Methods //
    ///////////////////////////
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Object> getKeys() {
        return Collections.unmodifiableList(keys);
    }

    @SuppressWarnings("unchecked")
    private void addChild(int idx, Object key, TreeNode child) {
        if (child instanceof TreeNodeImpl) {
            if (idx != -1) {
                keys.add(idx, key);
            } else {
                keys.add(key);
            }
            children.put(key, (TreeNodeImpl<T>) child);
        } else {
            throw new ClassCastException("The child is not a TreeNodeImpl<T>class object.");
        }
    }

    public LinkedHashMap<Object, TreeNodeImpl<T>> getChildren() {
        return children;
    }
}
