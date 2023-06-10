"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const webdriverio_1 = require("webdriverio");
describe('VSCode Live Server Extension', () => {
    let port;
    let browser;
    before('start browser', () => __awaiter(void 0, void 0, void 0, function* () {
        browser = yield (0, webdriverio_1.remote)({
            capabilities: { browserName: 'chrome' }
        });
        const workbench = yield driver.getWorkbench();
        /**
         * wait until all notifications are removed because sometimes
         * VSCode supresses notifications if others pop up
         */
        yield workbench.elem.waitUntil(() => __awaiter(void 0, void 0, void 0, function* () {
            const notifications = yield workbench.getNotifications();
            for (const n of notifications) {
                yield n.dismiss();
            }
            return (yield workbench.getNotifications()).length === 0;
        }));
    }));
    it('should click on Go Live', () => __awaiter(void 0, void 0, void 0, function* () {
        const workbench = yield driver.getWorkbench();
        yield workbench.elem.$('div[id="ritwickdey.LiveServer"]').click();
        yield workbench.elem.waitUntil(() => __awaiter(void 0, void 0, void 0, function* () {
            const notifications = yield workbench.getNotifications();
            for (const notification of notifications) {
                const message = yield notification.getMessage();
                if (message.startsWith('Server is Started')) {
                    port = parseInt(message.split(' : ')[1], 10);
                    return true;
                }
            }
            return false;
        }), { timeout: 10000 });
    }));
    it('should be able to open started server', () => __awaiter(void 0, void 0, void 0, function* () {
        yield browser.url(`http://localhost:${port}`);
        yield expect(browser).toHaveTitle('listing directory /');
    }));
    it('should show content of root directory and serve files', () => __awaiter(void 0, void 0, void 0, function* () {
        yield browser.$('span=README.md').click();
        expect(yield browser.getPageSource()).toContain('# Live Server');
    }));
    after('shutdown browser', () => {
        return browser.deleteSession();
    });
});
//# sourceMappingURL=example.e2e.js.map