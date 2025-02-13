import vue from '@vitejs/plugin-vue';
import path from 'path';
import { defineConfig } from 'vitest/config';

export default defineConfig({
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src/main/webapp/app'),
    },
  },
  plugins: [
    vue({
      template: {
        compilerOptions: {
          isCustomElement: tag => /^x-/.test(tag),
        },
      },
    }),
  ],
  test: {
    include: ['src/test/webapp/unit/**/*.spec.ts'],
    logHeapUsage: true,
    poolOptions: {
      threads: {
        minThreads: 1,
        maxThreads: 2,
      },
    },
    environment: 'jsdom',
    cache: false,
    reporters: ['default', 'vitest-sonar-reporter'],
    outputFile: {
      'vitest-sonar-reporter': 'target/test-results/TESTS-results-sonar.xml',
    },
    coverage: {
      all: false,
      provider: 'istanbul',
      reportsDirectory: 'target/frontend-coverage/unit-tests/',
      reporter: ['html', 'json', 'text-summary'],
    },
  },
});
