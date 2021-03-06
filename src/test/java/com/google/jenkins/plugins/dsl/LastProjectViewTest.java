/*
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.jenkins.plugins.dsl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.ImmutableList;

import hudson.model.ItemGroup;
import hudson.model.TopLevelItem;

/**
 * Tests for {@link LastProjectView}.
 */
public class LastProjectViewTest {
  @Rule
  public JenkinsRule jenkins = new JenkinsRule();

  @Rule
  public Retry retry = new Retry(3);

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testUnsupported_doCreateItem() throws Exception {
    LastProjectView underTest = new LastProjectView();
    underTest.doCreateItem(null /* req */, null /* rsp */);
  }

  // TODO(mattmoor): protected, tunnel through doConfigSubmit
  // @Test(expected = UnsupportedOperationException.class)
  // public void testUnsupported_submit() throws Exception {
  //   LastProjectView underTest = new LastProjectView();
  //   underTest.submit(null /* req */);
  // }

  @Test(expected = UnsupportedOperationException.class)
  public void testUnsupported_onJobRenamed() throws Exception {
    LastProjectView underTest = new LastProjectView();
    underTest.onJobRenamed(null /* item */, null /* oldName */,
        null /* newName */);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testUnsupported_contains() throws Exception {
    LastProjectView underTest = new LastProjectView();
    underTest.contains(null /* item */);
  }

  @Mock
  private ItemGroup mockItemGroup;

  @Mock
  private TopLevelItem mockItem;

  @Test
  public void testGetItems() throws Exception {
    List<TopLevelItem> list = ImmutableList.of(mockItem, mockItem);
    when(mockItemGroup.getItems()).thenReturn(list);
    LastProjectView underTest = new LastProjectView() {
        @Override
        public ItemGroup getOwnerItemGroup() {
          return mockItemGroup;
        }
      };

    assertEquals(0, underTest.getItems().size());

    verifyNoMoreInteractions(mockItemGroup);
    verifyNoMoreInteractions(mockItem);
  }
}