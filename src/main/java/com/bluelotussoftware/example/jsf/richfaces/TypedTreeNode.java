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
 * @param <T> The {@literal Type} of the class.
 * @author Nick Belaevski
 * @author John Yeary
 * @version 1.1
 *
 */
public class TypedTreeNode<T> implements TreeNode, Serializable {

    private static final long serialVersionUID = -5451984693315442917L;
    private T data;
    private final LinkedHashMap<Object, TypedTreeNode<T>> children = new LinkedHashMap<>();
    private final List<Object> keys = new ArrayList<>();

    /**
     * Default constructor
     */
    public TypedTreeNode() {
    }

    /**
     * Constructor that sets the data payload.
     *
     * @param data The data payload to be set.
     */
    public TypedTreeNode(T data) {
        this.data = data;
    }

    /**
     * Gets a child node using the provided key.
     *
     * @param key The key to be used to find the child node.
     * @return The node which the specified key is mapped, or {@literal null} if
     * this map contains no mapping for the key. is found.
     */
    @Override
    public TypedTreeNode<T> getChild(final Object key) {
        return children.get(key);
    }

    /**
     * Determine the index value of the given key.
     *
     * @param key The key value to determine index position.
     * @return The index of the first occurrence of the specified element in the
     * list of keys, or -1 if this key list does not contain the element.
     */
    @Override
    public int indexOf(Object key) {
        return keys.indexOf(key);
    }

    /**
     * This method returns an {@link Iterator} for any child elements associated
     * with this node.
     *
     * @return an {@link Iterator} which contains all of the child element keys,
     * or an empty {@link Iterator} if no keys exist.
     */
    @Override
    public Iterator<Object> getChildrenKeysIterator() {
        if (isLeaf()) {
            return Iterators.emptyIterator();
        }
        return Iterators.unmodifiableIterator(keys.iterator());
    }

    /**
     * Determines if the given node contains any children.
     *
     * @return {@literal true} if the node has no children, and {@literal false}
     * if the node has children.
     */
    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * Add a child element to the node.
     *
     * @param key The key to be used to identify the child.
     * @param child The {@link TreeNode} to be added to this node.
     * <p>
     * <strong>Note:</strong> Please note that the node to be added should be a
     * covariant {@link TypedTreeNode}, otherwise a {@link ClassCastException}
     * will be thrown.</p>
     */
    @Override
    public void addChild(Object key, TreeNode child) {
        addChild(-1, key, child);
    }

    /**
     * Inserts a child node at the given index position.
     *
     * @param idx The index position to be set.
     * @param key The key that identifies this node.
     * @param child The {@link TreeNode} to be added to this node.
     * <p>
     * <strong>Note:</strong> Please note that the node to be added should be a
     * covariant {@link TypedTreeNode}, otherwise a {@link ClassCastException}
     * will be thrown.</p>
     */
    @Override
    public void insertChild(int idx, Object key, TreeNode child) {
        addChild(idx, key, child);
    }

    /**
     * Removes a child node from this node.
     *
     * @param key The key that identifies the child element to remove.
     */
    @Override
    public void removeChild(Object key) {
        children.remove(key);
        keys.remove(key);
    }

    ///////////////////////////
    // Non Interface Methods //
    ///////////////////////////
    /**
     * Getter for data payload for this node.
     *
     * @return The data payload.
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data payload for this node.
     *
     * @param data The data payload to set.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * A list of all keys for child elements.
     *
     * @return A list of child element keys.
     */
    public List<Object> getKeys() {
        return Collections.unmodifiableList(keys);
    }

    /**
     * Adds a child node to the current node.
     *
     * @param idx The index position to place the child node.
     * @param key The key that identifies the child node.
     * @param child The {@link TreeNode} to be added to this node.
     * <p>
     * <strong>Note:</strong> Please note that the node to be added should be a
     * covariant {@link TypedTreeNode}, otherwise a {@link ClassCastException}
     * will be thrown.</p>
     */
    @SuppressWarnings("unchecked")
    private void addChild(int idx, Object key, TreeNode child) {
        if (child instanceof TypedTreeNode) {
            if (idx != -1) {
                keys.add(idx, key);
            } else {
                keys.add(key);
            }
            children.put(key, (TypedTreeNode<T>) child);
        } else {
            throw new ClassCastException("The child is not a TreeNodeImpl<T>class object.");
        }
    }

    /**
     * Returns all child elements.
     *
     * @return A {@link Map} of all children associated with the current node.
     */
    public LinkedHashMap<Object, TypedTreeNode<T>> getChildren() {
        return children;
    }
}
