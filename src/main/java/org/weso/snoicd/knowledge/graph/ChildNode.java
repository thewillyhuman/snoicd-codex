package org.weso.snoicd.knowledge.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildNode {
	private String snomedCode;
	private String icd9Code;
	private String icd10Code;
	private String description;
}
