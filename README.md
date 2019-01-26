<img src="https://github.com/thewilly/snoicd-codex/blob/master/docs/snoicd-codex-logo.png" alt="Snomed logo" height="50">

# SNOMED-CT & ICD Codex 

|| **Architecture** | **Status** | **Tests Coverage**
|:------:|:-:|:----------:|:---:|
|**Ubuntu Trusty 14.04**|x86_64|[![Build Status](https://travis-ci.org/thewilly/snoicd-codex.svg?branch=master)](https://travis-ci.org/thewilly/snoicd-codex)|[![codecov](https://codecov.io/gh/thewilly/snoicd-codex/branch/master/graph/badge.svg)](https://codecov.io/gh/thewilly/snoicd-codex)|

## Welcome to snoicd-codex
Snoicd codex is a REST service build for resolving and mapping the codes in SNOMED CT and ICD terminologies. It helps developers to step over the gap that exists between those two terminologies.

It is build on top of a reduced version of the SNOMED CT database and [UMLS](https://www.nlm.nih.gov/research/umls/) map files for ICD-9 and ICD-10.

## Versions included

| **Terminology Version** | **Internal code** |
|:------:|:-----:|
|ICD-9|`icd9`|
|ICD-10|`icd10`|
|SNOMED CT|`snomed`|
