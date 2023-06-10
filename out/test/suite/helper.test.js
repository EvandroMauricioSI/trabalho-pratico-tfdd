"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const assert = __importStar(require("assert"));
const Helper_1 = require("../../src/Helper");
suite('Helper Tests', () => {
    suite('Test for IsSupportedFile() method', () => {
        test('should return true for html file. e.g. file=index.html', () => {
            const file = 'index.html';
            const result = Helper_1.Helper.IsSupportedFile(file);
            assert.equal(result, true);
        });
        test('should return true for full path html file. e.g. file=/user/path/index.html', () => {
            const file = '/user/path/index.html';
            const result = Helper_1.Helper.IsSupportedFile(file);
            assert.equal(result, true);
        });
        test('should return true for svg file. e.g. file=index.svg', () => {
            const file = 'index.svg';
            const result = Helper_1.Helper.IsSupportedFile(file);
            assert.equal(result, true);
        });
        test('should return true for htm file. e.g. file=index.htm', () => {
            const file = 'index.htm';
            const result = Helper_1.Helper.IsSupportedFile(file);
            assert.equal(result, true);
        });
        test('should return false for XYZ file. e.g. file=index.xyz', () => {
            const file = 'index.xyz';
            const result = Helper_1.Helper.IsSupportedFile(file);
            assert.equal(result, false);
        });
        test('should return true for full path xyz file. e.g. file=/user/path/index.xyz', () => {
            const file = '/user/path/index.xyz';
            const result = Helper_1.Helper.IsSupportedFile(file);
            assert.equal(result, false);
        });
    });
    suite('Test for getSubPathIfSupported() method', () => {
        test('Should return correct Relative Path. format 1', () => {
            const targetPath = 'c:\\Users\\HTML\\cake\\index.html';
            const rootPath = 'c:\\Users\\HTML\\cake\\';
            const result = Helper_1.Helper.getSubPath(rootPath, targetPath);
            assert.equal(result, 'index.html');
        });
        test('Should return correct Relative Path. format 2', () => {
            const targetPath = 'c:\\Users\\HTML\\cake\\sub\\hello.html';
            const rootPath = 'c:\\Users\\HTML\\cake\\';
            const result = Helper_1.Helper.getSubPath(rootPath, targetPath);
            assert.equal(result, 'sub\\hello.html');
        });
        test('Should return null as targert file format is unsupported', () => {
            const targetPath = 'c:\\Users\\HTML\\cake\\sub\\hello.xyz';
            const rootPath = 'c:\\Users\\HTML\\cake\\';
            const result = Helper_1.Helper.getSubPath(rootPath, targetPath);
            assert.equal(result, null);
        });
    });
    suite('Test for generateParams() method', () => {
        test('should return correct parameters', () => {
            // const targetPath = 'c:\\Users\\HTML\\cake\\index.html';
            const rootPath = 'c:\\Users\\HTML\\cake\\';
            const port = 8080;
            const workspace = 'c:\\Users\\HTML\\';
            const ignorePathGlob = [];
            const result = Helper_1.Helper.generateParams(rootPath, workspace);
            // assert.equal(result.port, port); //next todo
            assert.equal(result.root, rootPath);
        });
    });
});
//# sourceMappingURL=helper.test.js.map